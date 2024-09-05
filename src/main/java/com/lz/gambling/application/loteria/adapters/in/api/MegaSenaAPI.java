package com.lz.gambling.application.loteria.adapters.in.api;

import com.lz.gambling.application.loteria.adapters.in.api.dtos.request.MegaSenaRequest;
import com.lz.gambling.application.loteria.adapters.in.api.dtos.response.MegaSenaResponse;
import com.lz.gambling.domain.loteriascaixa.usecase.CreateMegaSena;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/mega-sena")
public class MegaSenaAPI implements MegaSenaAPIAdapter {

    private CreateMegaSena createMegaSenaUseCase;

    @Autowired
    public MegaSenaAPI(final CreateMegaSena createMegaSenaUseCase) {
        this.createMegaSenaUseCase = createMegaSenaUseCase;
    }

    @Override
    public ResponseEntity<MegaSenaResponse> createMegaSena(final MegaSenaRequest megaSena) {
        return ResponseEntity.ok(
                MegaSenaResponse.fromDomain(createMegaSenaUseCase.execute(megaSena.toDomain())));
    }
}
