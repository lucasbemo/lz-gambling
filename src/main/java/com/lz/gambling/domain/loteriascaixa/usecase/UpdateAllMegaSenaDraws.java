package com.lz.gambling.domain.loteriascaixa.usecase;

import com.lz.gambling.application.loteria.adapters.out.postgres.entities.MegaSenaDrawsUpdateHistoryEntity;
import com.lz.gambling.domain.loteriascaixa.model.MegaSenaDrawsUpdateHistory;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateAllMegaSenaDraws {
    MegaSenaDrawsUpdateHistory execute(MultipartFile file);
}
