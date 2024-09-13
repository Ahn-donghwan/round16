package com.sparta.round16.service;

import com.sparta.round16.dto.comment.CommentResponseDto;
import com.sparta.round16.dto.comment.CommentSaveRequestDto;
import com.sparta.round16.dto.comment.CommentUpdateRequestDto;
import com.sparta.round16.entity.Comment;
import com.sparta.round16.entity.Todo;
import com.sparta.round16.repository.CommentRepository;
import com.sparta.round16.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    // step[*] : 트랜잭션
    @Transactional
    public CommentResponseDto saveCommentByTodoId(Long todoId, CommentSaveRequestDto commentSaveRequestDto) {
        // step[1] : todo 레퍼지토리에서 todoId 를 가지고 해당 todo 를 찾아서, todo 에 저장
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("해당 리소스를 찾을 수 없습니다."));

        // step[2] : RequestDto 의 데이터와 todo 의 데이터를 comment 에 저장
        Comment comment = new Comment(commentSaveRequestDto.getUsername(), commentSaveRequestDto.getContents(), todo);

        // step[3] : (데이터 정보가 담긴) comment 를 commentRepository 에 저장
        commentRepository.save(comment);

        // step[4] : comment 를 ResponseDto 로 반환
        return new CommentResponseDto(
                comment.getId(),
                comment.getUsername(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }

    public List<CommentResponseDto> getAllCommentByTodoId(Long todoId) {

        // step[1] : commentRepository 에서 todoId 에 해당하는 모든 comment 를 가지고 와서 List<Comment> 에 저장
        // step[2] : commentRepository 에 쿼리메서드 만들어주기
        List<Comment> commentList = commentRepository.findAllByTodoId(todoId);

        // step[3] : dto 를 만들 dtoList 를 생성
        List<CommentResponseDto> dtoList = new ArrayList<>();

        // step[4] : comment 의 타입은 Comment 이기 때문에, 하나씩 TodoResponseDto 타입으로 바꿔서 넣어주기 (commentList.for)
        for (Comment comment : commentList) {
            // step[5] dto 1개당 todo 정보 1개씩 짝지어서 넣어주기
            CommentResponseDto dto = new CommentResponseDto(
                    comment.getId(),
                    comment.getUsername(),
                    comment.getContents(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()
            );
            // step[6] : dtoList 에 dto 를 추가 (add)
            dtoList.add(dto);
        }
        // step[7] : dtoList 를 반환
        return  dtoList;
    }

    public CommentResponseDto getDetailCommentByCommentId(Long commentId) {

        // step[1] : commentRepository 에서 commentId 로 comment 를 찾아오고, 없다면 예외 처리
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("해당 리소스를 찾을 수 없습니다."));

        // step[2] : comment 를 CommentResponseDto 타입으로 반환(Return)
        return new CommentResponseDto(
                comment.getId(),
                comment.getUsername(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }

    // step[*] : 트랜잭션
    @Transactional
    public CommentResponseDto updateCommentByCommentId(
            Long commentId, CommentUpdateRequestDto commentUpdateRequestDto) {

        // step[1] : commentRepository 에서 commentId 로 comment 를 찾아오고, 없다면 예외 처리
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("해당 리소스를 찾을 수 없습니다."));

        // step[2-1] : comment 에 updateComment 메서드 만들어주기
        // step[2-2] : 만든 updateTodo 메서드를 사용해서 todo 에 RequestDto 데이터를 입력( == set == update )
        comment.updateComment(commentUpdateRequestDto.getUsername(), commentUpdateRequestDto.getContents());

        // step[3] : comment 를 CommentResponseDto 타입으로 반환(Return)
        return new CommentResponseDto(
                comment.getId(),
                comment.getUsername(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }

    // step[*] : 트랜잭션
    @Transactional
    public void deleteCommentByCommentId(Long commentId) {

        // step[1] : commentRepository 에서 commentId 로 comment 를 찾아오고, 업없다면 예외처리
        commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("해당 리소스를 찾을 수 없습니다."));

        // step[2] : commentId 로 comment 를 삭제(delete)
        commentRepository.deleteById(commentId);
    }
}
