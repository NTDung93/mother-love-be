package com.motherlove.services;

import com.motherlove.models.payload.dto.BlogDto;
import com.motherlove.models.payload.responseModel.CustomBlogResponse;
import org.springframework.data.domain.Page;

public interface IBlogService {
    CustomBlogResponse addBLog(BlogDto blogDto);
    CustomBlogResponse getBlog(Long id);
    Page<CustomBlogResponse> getAllBlogs(int pageNo, int pageSize, String sortBy, String sortDir);
    Page<CustomBlogResponse> searchBlogs(int pageNo, int pageSize, String sortBy, String sortDir, String searchText);
    CustomBlogResponse updateBlog(BlogDto blogDto);
    void deleteBlog(long id);
}
