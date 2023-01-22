package es.codeurjc.books.services.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import es.codeurjc.books.dtos.requests.CommentRequestDto;
import es.codeurjc.books.dtos.responses.CommentResponseDto;
import es.codeurjc.books.dtos.responses.UserCommentResponseDto;
import es.codeurjc.books.exceptions.ProductNotFoundException;
import es.codeurjc.books.exceptions.CommentNotFoundException;
import es.codeurjc.books.exceptions.UserNotFoundException;
import es.codeurjc.books.models.Product;
import es.codeurjc.books.models.Comment;
import es.codeurjc.books.models.User;
import es.codeurjc.books.repositories.ProductRepository;
import es.codeurjc.books.repositories.CommentRepository;
import es.codeurjc.books.repositories.UserRepository;
import es.codeurjc.books.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    private Mapper mapper;
    private CommentRepository commentRepository;
    private ProductRepository bookRepository;
    private UserRepository userRepository;

    public CommentServiceImpl(Mapper mapper, CommentRepository commentRepository, ProductRepository bookRepository,
                              UserRepository userRepository) {
        this.mapper = mapper;
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public CommentResponseDto addComment(long bookId, CommentRequestDto commentRequestDto) {
        Product book = this.bookRepository.findById(bookId).orElseThrow(ProductNotFoundException::new);
        User user = this.userRepository.findByNick(commentRequestDto.getUserNick()).orElseThrow(UserNotFoundException::new);
        Comment comment = this.mapper.map(commentRequestDto, Comment.class);
        comment.setBook(book);
        comment.setUser(user);
        comment = this.commentRepository.save(comment);
        return this.mapper.map(comment, CommentResponseDto.class);
    }

    public CommentResponseDto deleteComment(long bookId, long commentId) {
        Comment comment = this.commentRepository.findByBookIdAndId(bookId, commentId)
                .orElseThrow(CommentNotFoundException::new);
        this.commentRepository.delete(comment);
        return this.mapper.map(comment, CommentResponseDto.class);
    }

    public Collection<UserCommentResponseDto> getComments(long userId) {
        return this.commentRepository.findByUserId(userId).stream()
                .map(comment -> this.mapper.map(comment, UserCommentResponseDto.class))
                .collect(Collectors.toList());
    }

}
