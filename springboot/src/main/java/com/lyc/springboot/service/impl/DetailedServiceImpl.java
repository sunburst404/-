package com.lyc.springboot.service.impl;

import com.lyc.springboot.entity.Detailed;
import com.lyc.springboot.entity.Longid;
import com.lyc.springboot.entity.RegionCode;
import com.lyc.springboot.mapper.DetailedMapper;
import com.lyc.springboot.service.IDetailedService;
import com.lyc.springboot.service.ILongidService;
import com.lyc.springboot.service.IRegionCodeService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.Location;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyc
 * @since 2023-12-18
 */
@Service
public class DetailedServiceImpl extends ServiceImpl<DetailedMapper, Detailed> implements IDetailedService {

    @Resource
    private IDetailedService IDetailedService;

    @Resource
    private ILongidService ILongidService;

    @Resource
    private IRegionCodeService IRegionCodeService;

    @Override
    public Boolean add(Longid longid){
        try {
            // 这里实现将 Longid 转换为 Detailed，并保存到数据库
            Detailed detailed = convertToDetailed(longid);
            saveOrUpdate(detailed);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Detailed convertToDetailed(Longid longid) {
        Detailed detailed = new Detailed();

        detailed.setId(longid.getId());

        // 根据 Longid 的属性设置 Detailed 的属性
        //地址
        String locationId = longid.getId().substring(0, 12);
        RegionCode rc = IRegionCodeService.getById(locationId);
        String location = rc.getProvince()+rc.getCity()+rc.getCounty()+rc.getTown()+rc.getVillage();
        detailed.setLocation(location);
        //时间
        String dataCode = longid.getId().substring(12, 26);

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            java.util.Date parsedDate = format.parse(dataCode);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());

            //System.out.println(timestamp);
            detailed.setDate(timestamp);
        //    System.out.println(v.getDate());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //origin
        String origin = longid.getId().substring(26,29);
        String originCode =SourceCodeParser(origin);
        detailed.setOrigin(originCode);

        //set carrier
        String carrier = detailed.getId().substring(29,30);
        switch(carrier) {
            case"0":
                detailed.setCarrier("文字");
                break;
            case"1":
                detailed.setCarrier("图像");

                break;
            case"2":
                detailed.setCarrier("音频");

                break;
            case"3":
                detailed.setCarrier("视频");

                break;
            default:
                detailed.setCarrier("其他");
        }

        //set category
        String category = detailed.getId().substring(30,33);
        detailed.setCategory(DisasterCodeParser(category));

        //set label
        String label = detailed.getId().substring(33,36);
        detailed.setLabel(LabelCodeParser(label));

        detailed.setDescription(longid.getDescription());
        // 其他属性设置...
        return detailed;
    }

    public String DisasterCodeParser(String disasterCode) {
        int mainCategoryCode1 = Integer.parseInt(disasterCode.substring(0, 1));
        int subCategoryCode1 = Integer.parseInt(disasterCode.substring(1, 3));
        String mainCategory1, subCategory1;
        switch (mainCategoryCode1) {
            case 1:
                mainCategory1 = "震情";
                switch (subCategoryCode1) {
                    case 1:
                        subCategory1 = "震情信息";
                        break;
                    default:
                        subCategory1 = "  ";
                }
                break;
            case 2:
                mainCategory1 = "人员伤亡及失踪";
                switch (subCategoryCode1) {
                    case 1:
                        subCategory1 = "死亡";
                        break;
                    case 2:
                        subCategory1 = "受伤";
                        break;
                    case 3:
                        subCategory1 = "失踪";
                        break;
                    default:
                        subCategory1 = "  ";
                        break;
                }
                break;
            case 3:
                mainCategory1 = "房屋破坏";
                switch (subCategoryCode1) {
                    case 1:
                        subCategory1 = "土木";
                        break;
                    case 2:
                        subCategory1 = "砖木";
                        break;
                    case 3:
                        subCategory1 = "砖混";
                        break;
                    case 4:
                        subCategory1 = "框架";
                        break;
                    default:
                        subCategory1 = "其他";
                        break;
                }
                break;
            case 4:
                mainCategory1 = "生命线工程灾情";
                switch (subCategoryCode1) {
                    case 1:
                        subCategory1 = "交通";
                        break;
                    case 2:
                        subCategory1 = "供水";
                        break;
                    case 3:
                        subCategory1 = "输油";
                        break;
                    case 4:
                        subCategory1 = "燃气";
                        break;
                    case 5:
                        subCategory1 = "电力";
                        break;
                    case 6:
                        subCategory1 = "通信";
                        break;
                    case 7:
                        subCategory1 = "水利";
                        break;
                    default:
                        subCategory1 = "  ";
                        break;
                }
                break;
            case 5:
                mainCategory1 = "次生灾害";
                switch (subCategoryCode1) {
                    case 1:
                        subCategory1 = "崩塌";
                        break;
                    case 2:
                        subCategory1 = "滑坡";
                        break;
                    case 3:
                        subCategory1 = "泥石流";
                        break;
                    case 4:
                        subCategory1 = "岩溶塌陷";
                        break;
                    case 5:
                        subCategory1 = "地裂缝";
                        break;
                    case 6:
                        subCategory1 = "地面沉降";
                        break;
                    default:
                        subCategory1 = "其他（沙土液化、火灾、毒气泄露、爆炸、环境污染、瘟疫、海啸等）";
                        break;

                }
                break;
            default:
                mainCategory1 = "  ";
                subCategory1 = "  ";
                break;
        }
        return mainCategory1+subCategory1;
    }

    public String LabelCodeParser(String LabelCode) {
        int mainCategory = Integer.parseInt(LabelCode.substring(0,1));
        int subCategory = Integer.parseInt(LabelCode.substring(1));
        String mainCategoryDescription, subCategoryDescription;
        switch (mainCategory) {
            case 1:
                mainCategoryDescription = "地震事件信息";
                switch (subCategory) {
                    case 1:
                        subCategoryDescription = "地理位置";
                        break;
                    case 2:
                        subCategoryDescription = "时间";
                        break;
                    case 3:
                        subCategoryDescription = "震级";
                        break;
                    case 4:
                        subCategoryDescription = "震源深度";
                        break;
                    case 5:
                        subCategoryDescription = "烈度";
                        break;
                    default:
                        subCategoryDescription="  ";
                }
                break;
            case 2:
                mainCategoryDescription = "人员伤亡及失踪信息";
                switch (subCategory) {
                    case 1:
                        subCategoryDescription = "受灾人数";
                        break;
                    case 2:
                        subCategoryDescription = "受灾程度";
                        break;
                    default:
                        subCategoryDescription="  ";
                }
                break;
            case 3:
                mainCategoryDescription = "房屋破坏信息";
                switch (subCategory) {
                    case 1:
                        subCategoryDescription = "一般损坏面积";
                        break;
                    case 2:
                        subCategoryDescription = "严重损坏面积";
                        break;
                    case 3:
                        subCategoryDescription = "受灾程度";
                        break;
                    default:
                        subCategoryDescription="  ";
                }
                break;
            case 4:
                mainCategoryDescription = "生命线工程灾情信息";
                switch (subCategory) {
                    case 1:
                        subCategoryDescription = "受灾设施数";
                        break;
                    case 2:
                        subCategoryDescription = "受灾范围";
                        break;
                    case 3:
                        subCategoryDescription = "受灾程度";
                        break;
                    default:
                        subCategoryDescription="  ";
                }
                break;
            case 5:
                mainCategoryDescription = "次生灾害信息";
                switch (subCategory) {
                    case 1:
                        subCategoryDescription = "灾害损失";
                        break;
                    case 2:
                        subCategoryDescription = "灾害范围";
                        break;
                    case 3:
                        subCategoryDescription = "受灾程度";
                        break;
                    default:
                        subCategoryDescription="  ";
                }
                break;
            default: {
                mainCategoryDescription = "  ";
                subCategoryDescription = "  ";
                break;
            }
        }

        return mainCategoryDescription+subCategoryDescription;
    }

    private String SourceCodeParser(String origin) {
        int mainCategory = Integer.parseInt(origin.substring(0, 1));
        int subCategory = Integer.parseInt(origin.substring(1));

        String mainCategoryName;

        switch (mainCategory) {
            case 1:
                mainCategoryName = "业务报送数据";
            case 2:
                mainCategoryName = "泛在感知数据";
            case 3:
                mainCategoryName = "其他数据";
            default:
                mainCategoryName =  "未知";
        }

        switch (mainCategory) {
            case 1:
                switch (subCategory) {
                    case 0:
                        mainCategoryName += "前方地震应急指挥部";
                        break;
                    case 1:
                        mainCategoryName += "后方地震应急指挥部";
                        break;
                    case 20:
                        mainCategoryName += "应急指挥技术系统";
                        break;
                    case 21:
                        mainCategoryName += "社会服务工程应急救援系统";
                        break;
                    case 40:
                        mainCategoryName += "危险区预评估工作组";
                        break;
                    case 41:
                        mainCategoryName += "地震应急指挥技术协调组";
                        break;
                    case 42:
                        mainCategoryName += "震后政府信息支持工作项目组";
                        break;
                    case 80:
                        mainCategoryName += "灾情快速上报接收处理系统";
                        break;
                    case 81:
                        mainCategoryName += "地方地震局应急信息服务相关技术系统";
                        break;
                    case 99:
                        mainCategoryName += "其他";
                        break;
                    default:
                        mainCategoryName += "  ";
                        break;
                }
                break;
            case 2:
                switch (subCategory) {
                    case 0:
                        mainCategoryName += "互联网感知";
                        break;
                    case 1:
                        mainCategoryName += "通信网感知";
                        break;
                    case 2:
                        mainCategoryName += "舆情网感知";
                        break;
                    case 3:
                        mainCategoryName += "电力系统感知";
                        break;
                    case 4:
                        mainCategoryName += "交通系统感知";
                        break;
                    case 5:
                        mainCategoryName += "其他";
                        break;
                    default:
                        mainCategoryName += "  ";
                        break;
                }
            default:
                mainCategoryName += "未知";
        }
        return mainCategoryName;
    }


    public Boolean addXml(MultipartFile file) {
        try {
            // 创建DocumentBuilderFactory对象
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            // 创建DocumentBuilder对象
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            // 使用DocumentBuilder对象解析文件的输入流，得到Document对象
            Document doc = dBuilder.parse(file.getInputStream());

            // 规范化XML文档
            doc.getDocumentElement().normalize();

            // 打印根元素的名称
            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            // 获取名为"yourTag"的元素的列表
            NodeList nList = doc.getElementsByTagName("RECORD");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Longid longid = new Longid();
                Node idNode = nList.item(temp);
                // 如果当前节点是元素节点
                if (idNode.getNodeType() == Node.ELEMENT_NODE) {
                    // 将节点转换为元素
                    Element eElement = (Element) idNode;
                    // 打印"yourData"元素的内容
                    longid.setId(eElement.getElementsByTagName("id").item(0).getTextContent());
                    longid.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());

                    System.out.println("从xml得到"+longid.getId());
                    ILongidService.save(longid);
                    add(longid);
                }
            }
            return true;
        } catch (Exception e) {
            // 打印异常堆栈
            return false;
        }
    }





}
