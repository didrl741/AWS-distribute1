package study.jpastudy.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jpastudy.domain.posts.Posts;
import study.jpastudy.domain.posts.PostsRepository;
import study.jpastudy.web.dto.PostsResponseDto;
import study.jpastudy.web.dto.PostsSaveRequestDto;
import study.jpastudy.web.dto.PostsUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없어요. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }

    public List<PostsResponseDto> findAll() {

        List<Posts> findPosts = postsRepository.findAll();

        List<PostsResponseDto> collect = findPosts.stream()
                .map(p-> new PostsResponseDto(p))
                .collect(Collectors.toList());

        return collect;
    }
}
