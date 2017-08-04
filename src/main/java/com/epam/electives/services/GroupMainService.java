package com.epam.electives.services;

import com.epam.electives.dao.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class GroupMainService {
    @Autowired
    GroupDao groupDao;
}

