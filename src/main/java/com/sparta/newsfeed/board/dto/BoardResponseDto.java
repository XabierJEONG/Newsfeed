package com.sparta.newsfeed.board.dto;

import com.sparta.newsfeed.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto {

    private String content;
    private LocalDateTime createdAt;

    public BoardResponseDto(Board Board) {
        this.content = Board.getContent();
        this.createdAt = Board.getCreatedAt();
    }
}
