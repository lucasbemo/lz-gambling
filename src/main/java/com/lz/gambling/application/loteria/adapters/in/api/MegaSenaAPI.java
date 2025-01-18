package com.lz.gambling.application.loteria.adapters.in.api;

import com.lz.gambling.application.loteria.adapters.in.api.dtos.request.MegaSenaRequest;
import com.lz.gambling.application.loteria.adapters.in.api.dtos.response.MegaSenaResponse;
import com.lz.gambling.application.loteria.adapters.out.postgres.entities.MegaSenaDrawsUpdateHistoryEntity;
import com.lz.gambling.domain.loteriascaixa.model.MegaSenaDrawsUpdateHistory;
import com.lz.gambling.domain.loteriascaixa.usecase.CreateMegaSena;
import com.lz.gambling.domain.loteriascaixa.usecase.UpdateAllMegaSenaDraws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/v1/mega-sena")
public class MegaSenaAPI implements MegaSenaAPIAdapter {

    private final CreateMegaSena createMegaSenaUseCase;
    private final UpdateAllMegaSenaDraws updateAllMegaSenaDrawsUseCase;

    @Autowired
    public MegaSenaAPI(final CreateMegaSena createMegaSenaUseCase
            , final UpdateAllMegaSenaDraws updateAllMegaSenaDrawsUseCase) {
        this.createMegaSenaUseCase = createMegaSenaUseCase;
        this.updateAllMegaSenaDrawsUseCase = updateAllMegaSenaDrawsUseCase;
    }

    @Override
    public ResponseEntity<MegaSenaResponse> createMegaSena(final MegaSenaRequest megaSena) {
        return ResponseEntity.ok(
                MegaSenaResponse.fromDomain(createMegaSenaUseCase.execute(megaSena.toDomain())));
    }

    @Override
    public ResponseEntity<MegaSenaDrawsUpdateHistory> updateAllDraws
            (@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        return ResponseEntity.ok(updateAllMegaSenaDrawsUseCase.execute(file));
    }
}
