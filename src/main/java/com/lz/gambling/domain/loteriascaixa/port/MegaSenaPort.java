package com.lz.gambling.domain.loteriascaixa.port;

import com.lz.gambling.domain.loteriascaixa.model.MegaSena;

import java.util.List;

public interface MegaSenaPort {

    MegaSena save(final MegaSena megasena);
    List<MegaSena> list();
    MegaSena find(final Long id);
    MegaSena update(final MegaSena megasena);
    MegaSena delete(final Long id);
}
