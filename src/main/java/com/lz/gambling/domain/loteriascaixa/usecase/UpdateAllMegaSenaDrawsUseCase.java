package com.lz.gambling.domain.loteriascaixa.usecase;

import com.lz.gambling.application.loteria.adapters.out.postgres.entities.MegaSenaDrawsUpdateHistoryEntity;
import com.lz.gambling.application.loteria.services.HandleMegaSenaFile;
import com.lz.gambling.domain.loteriascaixa.model.MegaSena;
import com.lz.gambling.domain.loteriascaixa.model.MegaSenaDrawsUpdateHistory;
import com.lz.gambling.domain.loteriascaixa.port.MegaSenaDrawsPort;
import com.lz.gambling.domain.loteriascaixa.port.MegaSenaDrawsUpdateHistorPort;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UpdateAllMegaSenaDrawsUseCase implements UpdateAllMegaSenaDraws {

    private final HandleMegaSenaFile storageService;
    private final MegaSenaDrawsUpdateHistorPort megaSenaDrawsUpdateHistorPort;
    private final MegaSenaDrawsPort megaSenaDrawsPort;

    public UpdateAllMegaSenaDrawsUseCase(final HandleMegaSenaFile storageService
            , final MegaSenaDrawsUpdateHistorPort megaSenaDrawsUpdateHistorPort
            , final MegaSenaDrawsPort megaSenaDrawsPort) {
        this.storageService = storageService;
        this.megaSenaDrawsUpdateHistorPort = megaSenaDrawsUpdateHistorPort;
        this.megaSenaDrawsPort = megaSenaDrawsPort;
    }

    @Override
    public MegaSenaDrawsUpdateHistory execute(final MultipartFile file) {
        // Store file
        storageService.store(file);

        MegaSenaDrawsUpdateHistoryEntity MegaSenaDrawsUpdateHistoryEntity =
                new MegaSenaDrawsUpdateHistoryEntity(null, "file", LocalDateTime.now(), file.getOriginalFilename());
        MegaSenaDrawsUpdateHistory megaSenaDrawsUpdateHistory = megaSenaDrawsUpdateHistorPort.execute(MegaSenaDrawsUpdateHistoryEntity);

        saveAllRegistries(file, megaSenaDrawsUpdateHistory);

        return megaSenaDrawsUpdateHistory;
    }

    private void saveAllRegistries(final MultipartFile file, final MegaSenaDrawsUpdateHistory megaSenaDrawsUpdateHistory) {
        List<MegaSena> savedList = megaSenaDrawsPort.list();

        List<MegaSena> itensFromFile = readAllRegisters(file, megaSenaDrawsUpdateHistory);

        List<MegaSena> listToSave = itensFromFile.stream().filter( item -> {
            return savedList.stream().noneMatch(subItem -> {
                return subItem.dataSorteio().equals(item.dataSorteio());
            });
        }).collect(Collectors.toList());

        System.out.println("listToSave - " + listToSave.size());

        // Save all registers the was not saved before.
        megaSenaDrawsPort.saveAll(listToSave);
    }

    private List<MegaSena> readAllRegisters(final MultipartFile file, final MegaSenaDrawsUpdateHistory megaSenaDrawsUpdateHistory) {
        List<MegaSena> resultList = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) rowIterator.next(); // Skip header

            int idx=0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                //TODO
                if (idx==2000)
                    break;
                idx++;

                MegaSena megaSena = new MegaSena(
                        null, // Assuming id is not available in the file
                        (int) row.getCell(0).getNumericCellValue(),
                        LocalDate.parse(row.getCell(1).toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        (int) row.getCell(2).getNumericCellValue(),
                        (int) row.getCell(3).getNumericCellValue(),
                        (int) row.getCell(4).getNumericCellValue(),
                        (int) row.getCell(5).getNumericCellValue(),
                        (int) row.getCell(6).getNumericCellValue(),
                        (int) row.getCell(7).getNumericCellValue(),
                        (int) row.getCell(8).getNumericCellValue(),
                        row.getCell(9).getStringCellValue(),
                        Double.parseDouble(row.getCell(10).getStringCellValue()
                                .replace("R$", "").replace(".", "")
                                .replace(",", ".").trim()),
                        (int) row.getCell(11).getNumericCellValue(),
                        Double.parseDouble(row.getCell(12).getStringCellValue()
                                .replace("R$", "").replace(".", "")
                                .replace(",", ".").trim()),
                        (int) row.getCell(13).getNumericCellValue(),
                        Double.parseDouble(row.getCell(14).getStringCellValue()
                                .replace("R$", "").replace(".", "")
                                .replace(",", ".").trim()),
                        Double.parseDouble(row.getCell(15).getStringCellValue()
                                .replace("R$", "").replace(".", "")
                                .replace(",", ".").trim()),
                        Double.parseDouble(row.getCell(16).getStringCellValue()
                                .replace("R$", "").replace(".", "")
                                .replace(",", ".").trim()),
                        Double.parseDouble(row.getCell(17).getStringCellValue()
                                .replace("R$", "").replace(".", "")
                                .replace(",", ".").trim()),
                        Double.parseDouble(row.getCell(18).getStringCellValue()
                                .replace("R$", "").replace(".", "")
                                .replace(",", ".").trim()),
                        row.getCell(19).getStringCellValue(),
                        megaSenaDrawsUpdateHistory
                );

                resultList.add(megaSena);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
