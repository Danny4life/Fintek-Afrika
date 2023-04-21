package com.osiki.finteckafrika.service.serviceImpl;

import com.osiki.finteckafrika.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
@Builder
public class UsersServiceImpl implements UsersService {
}
