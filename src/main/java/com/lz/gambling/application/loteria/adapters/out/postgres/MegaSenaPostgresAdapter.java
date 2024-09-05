package com.lz.gambling.application.loteria.adapters.out.postgres;

import com.lz.gambling.application.loteria.adapters.out.postgres.entities.MegaSenaEntity;
import com.lz.gambling.application.loteria.adapters.out.postgres.repositories.MegaSenaJPARepository;
import com.lz.gambling.domain.loteriascaixa.model.MegaSena;
import com.lz.gambling.domain.loteriascaixa.port.MegaSenaPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MegaSenaPostgresAdapter implements MegaSenaPort {

    private MegaSenaJPARepository megaSenaJPARepository;

    @Autowired
    public MegaSenaPostgresAdapter(final MegaSenaJPARepository megaSenaJPARepository) {
        this.megaSenaJPARepository = megaSenaJPARepository;
    }

    @Override
    public MegaSena save(final MegaSena megaSena) {
        return megaSenaJPARepository.save(MegaSenaEntity.fromDomain(megaSena)).toDomain();
    }

    @Override
    public List<MegaSena> list() {
        return List.of();
    }

    @Override
    public MegaSena find(final Long id) {
        return null;
    }

    @Override
    public MegaSena update(final MegaSena megasena) {
        return null;
    }

    @Override
    public MegaSena delete(final Long id) {
        return null;
    }
}
