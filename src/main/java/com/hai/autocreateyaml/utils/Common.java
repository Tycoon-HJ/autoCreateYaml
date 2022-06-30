package com.hai.autocreateyaml.utils;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <img src = "https://patchwiki.biligame.com/images/ys/7/73/793kuoybdf409lnzwevsmd8ipnel1d2.png"/>
 * @author YinHaiJun
 * @program autoCreateYaml
 * @description 工具类 TODO 第一版本先不用，后续优化
 * @create 2022-06-12 13:31
 **/
public class Common {

    public static List<String> getDocument(String filePath) {

        // 这个List存放所有依赖的名称，之后根据依赖添加yaml里的配置
        List<String> dependencies = new ArrayList<>();

        SAXBuilder saxBuilder = new SAXBuilder();
        Document build;
        try {
            build = saxBuilder.build(new File(filePath));
        } catch (Exception e) {
            // 打印一个错误的通知
            Notification notification = new Notification("autoCreateYaml.notificationGroup", "该插件只支持在选中pom.xml文件右击才会生效哦～", NotificationType.ERROR);
            Notifications.Bus.notify(notification);
            return Collections.emptyList();
        }
        Element rootElement = build.getRootElement();
        List<Element> children = rootElement.getChildren();
        for (Element element : children) {
            if ("dependencies".equals(element.getName())){
                List<Element> children1 = element.getChildren();
                for (Element item : children1) {
                    List<Element> children2 = item.getChildren();
                    for (Element value : children2) {
                        if ("artifactId".equals(value.getName())) {
                            dependencies.add(value.getText());
                        }
                    }
                }
            }
        }
        return dependencies;
    }
}
