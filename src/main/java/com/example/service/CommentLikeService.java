package com.example.service;

import com.example.dto.CommentLikeDTO;
import com.example.entity.CommentLikeEntity;
import com.example.enums.CommentLikeStatus;
import com.example.repository.CommentLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentLikeService {
    @Autowired
    private CommentLikeRepository commentLikeRepository;

    public void create(CommentLikeDTO dto) {
        CommentLikeEntity exists = get(dto);
        if (exists != null) {
            updateStatus(dto);
            return;
        }
        CommentLikeEntity entity = new CommentLikeEntity();
        entity.setCommentId(dto.getCommentId());
        entity.setProfileId(dto.getProfileId());
        entity.setStatus(dto.getStatus());
        commentLikeRepository.save(entity);
    }

    public void updateStatus(CommentLikeDTO dto) {
        CommentLikeEntity entity1 = get(dto);

        if (entity1.getStatus().equals(dto.getStatus())) {
            delete(dto);
            return;
        } else if (entity1.getStatus().equals(CommentLikeStatus.LIKE)) {
            dto.setStatus(CommentLikeStatus.DISLIKE);
        } else if (entity1.getStatus().equals(CommentLikeStatus.DISLIKE)) {
            dto.setStatus(CommentLikeStatus.LIKE);
        }
        entity1.setStatus(dto.getStatus());
        commentLikeRepository.save(entity1);

    }

    public CommentLikeEntity get(CommentLikeDTO dto) {
        return commentLikeRepository.findByProfileIdAndCommentId(dto.getProfileId(),
                dto.getCommentId());
    }

    public void delete(CommentLikeDTO dto) {
        commentLikeRepository.deleted(dto.getProfileId(), dto.getCommentId());
    }
}
