package com.lz.gambling.application.loteria.adapters.in.api;

import com.lz.gambling.application.loteria.adapters.in.api.dtos.request.MegaSenaRequest;
import com.lz.gambling.application.loteria.adapters.in.api.dtos.response.MegaSenaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface MegaSenaAPIAdapter {

    ResponseEntity<MegaSenaResponse> createMegaSena(@RequestParam final MegaSenaRequest megaSena);
}
