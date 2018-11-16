package com.devkidult.communityweb.service;

import com.devkidult.communityweb.domain.Board;
import com.devkidult.communityweb.repo.BoardRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private final BoardRepo boardRepo;

    public BoardService(BoardRepo boardRepo) {
        this.boardRepo = boardRepo;
    }

    public Page<Board> findBoardList(Pageable pageable){
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1 , pageable.getPageSize());
        return boardRepo.findAll(pageable);
    }

    public Board findBoardbyIdx(Long idx){
        return boardRepo.findById(idx).orElse(new Board());
    }
}
