package com.hai.autocreateyaml;

import com.hai.autocreateyaml.utils.ApplicationStr;
import com.hai.autocreateyaml.utils.StaticString;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.psi.PsiFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author mr.ahai
 */
public class AutoCreateYaml extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {

        Notification notification = new Notification(StaticString.GROUP_ID, "请在pom文化上点击右键生成yaml文件：", NotificationType.INFORMATION);
        Notifications.Bus.notify(notification);

        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);
        String classPath;
        if (psiFile != null && psiFile.getVirtualFile() != null) {
            classPath = psiFile.getVirtualFile().getPath();
        } else {
            return;
        }


        File file = new File(classPath.replace("pom.xml", "src/main/resources/application.yml"));
        if (!file.exists()) {
            try {
                boolean newFile = file.createNewFile();
                Notifications.Bus.notify(new Notification(StaticString.GROUP_ID, "创建文件成功了吗？ " + newFile, NotificationType.INFORMATION));
            } catch (IOException ex) {
                Notifications.Bus.notify(new Notification(StaticString.GROUP_ID, "创建文件出错了，妈的", NotificationType.ERROR));
            }
        }

        // 将数据写入文档
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(ApplicationStr.APPLICATION_YAML.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ex) {
            Notifications.Bus.notify(new Notification(StaticString.GROUP_ID, "写入数据出错了，妈的", NotificationType.ERROR));
        }
    }
}
