package com.own.cyberpunk.service.serviceImpl;

import com.own.cyberpunk.dto.GunDto;
import com.own.cyberpunk.service.GunService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GunServiceImpl implements GunService {
    @Override
    public List<GunDto> getGuns() {
        return List.of();
    }
}
