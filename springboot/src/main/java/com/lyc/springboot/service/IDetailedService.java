package com.lyc.springboot.service;

import com.lyc.springboot.entity.Detailed;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyc.springboot.entity.Longid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lyc
 * @since 2023-12-18
 */
public interface IDetailedService extends IService<Detailed> {


    Boolean add(Longid longId);

    Boolean addXml(MultipartFile file);
}
