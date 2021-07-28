package com.example.jpapagingexam.paging.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardService {

    public int getOffset(Optional<Integer> optionalOffset) {
        int offset = 0;
        if (optionalOffset.isPresent()) {
            offset = optionalOffset.get() - 1;
        }

        return offset;
    }

}
