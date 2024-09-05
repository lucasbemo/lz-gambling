package com.lz.gambling.domain.loteriascaixa.usecase;

import com.lz.gambling.domain.loteriascaixa.model.MegaSena;
import com.lz.gambling.domain.loteriascaixa.port.MegaSenaPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateMegaSenaUseCase implements CreateMegaSena {

    @Autowired
    private MegaSenaPort megaSenaPort;

    @Override
    public MegaSena execute(final MegaSena megasena) {
        return megaSenaPort.save(megasena);
    }
}
