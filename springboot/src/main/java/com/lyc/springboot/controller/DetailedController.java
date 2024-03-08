package com.lyc.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.springboot.config.Result;
import com.lyc.springboot.entity.Longid;
import com.lyc.springboot.entity.RegionCode;
import com.lyc.springboot.mapper.RegionCodeMapper;
import com.lyc.springboot.service.impl.DetailedServiceImpl;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.lyc.springboot.service.IDetailedService;
import com.lyc.springboot.service.ILongidService;
import com.lyc.springboot.entity.Detailed;
import org.springframework.web.multipart.MultipartFile;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lyc
 * @since 2023-12-18
 */
@RestController
@RequestMapping("/detailed")
        public class DetailedController {

    @Resource
        private IDetailedService detailedService;

    @Resource
    private ILongidService longidService;

    @PostMapping
        public Boolean save(@RequestBody Detailed detailed) {
        return detailedService.saveOrUpdate(detailed);
        }
    @DeleteMapping("/{id}")
        public Boolean delete(@PathVariable Integer id) {
        return detailedService.removeById(id);
        }
    @PostMapping("/del/batch")
        public boolean deleteBatch(@RequestBody List<Integer> ids){
        return detailedService.removeBatchByIds(ids);
        }

    @PostMapping("/add")
    public Boolean add(@RequestParam String id,
                       @RequestParam String description
                       ) {
        System.out.println(id);
            Longid longId = new Longid();
            longId.setId(id);
            longId.setDescription(description);
        System.out.println("1111111111111111111111111");
        System.out.println(longId.getId());
            longidService.save(longId);

            return detailedService.add(longId);
    }

    @Resource
    public RegionCodeMapper regionCodeRepository;

    @PostMapping("/addDetail")
    public Boolean addDetail(@RequestParam String idNoAddress, @RequestParam String description,
                             @RequestParam String town,@RequestParam String village) {

        System.out.println(idNoAddress);
        System.out.println(village);
        String id = regionCodeRepository.findVillageCode(village,town);
        id=id+idNoAddress;
        System.out.println(id);
        Longid longId=new Longid();
        longId.setId(id);
        longId.setDescription(description);
        longidService.save(longId);
        return detailedService.add(longId);
    }

    @PostMapping("/addXml")
    public Boolean addXml(@RequestParam("file") MultipartFile file){
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        return detailedService.addXml(file);
    }


    @GetMapping
        public List<Detailed> findAll() {
        return detailedService.list();
        }

        @GetMapping("/{id}")
            public Detailed  findOne(@PathVariable Integer id) {
        return detailedService.getById(id);
        }



        @GetMapping("/page")
            public Page<Detailed> findPage(@RequestParam Integer pageNum,
                                           @RequestParam Integer pageSize,
                                           @RequestParam(defaultValue = "") String id,
                                           @RequestParam(defaultValue = "") String location
        ) {
            QueryWrapper<Detailed> queryWrapper = new QueryWrapper<>();
            if(!"".equals(id)){
                queryWrapper.like("id",id);
            }
            if(!"".equals(location)){
                queryWrapper.like("location",location);
            }
            queryWrapper.orderByDesc("id");

        return detailedService.page(new Page<>(pageNum, pageSize),queryWrapper);
        }



        }

