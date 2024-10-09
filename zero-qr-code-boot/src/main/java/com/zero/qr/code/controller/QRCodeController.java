package com.zero.qr.code.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.zero.qr.code.common.util.QRCodeGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    @RequestMapping("base64")
    public Map<String, Object> qrcodeBase64(String orderNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderNo", orderNo);
        try {
            String message = QRCodeGenerator.writeToStream(orderNo, 350, 350);
            map.put("message", message);
            map.put("code", 200);
            map.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 500);
            map.put("status", "error");
        }
        return map;
    }

    @RequestMapping("image1")
    public Map<String, Object> qrcodeImage1(String orderNo) {
        Map<String, Object> map = new HashMap<>();
        String failPath = "D:\\Sevendays\\" + orderNo + ".png";
        map.put("failPath", failPath);
        map.put("message", orderNo);
        try {
            QRCodeGenerator.generateQRCodeImage(orderNo, 350, 350, map.get("failPath").toString());
            map.put("code", 200);
            map.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 500);
            map.put("status", "error");
        }
        return map;
    }

    @RequestMapping("image2")
    public Map<String, Object> qrcodeImage2() {
        Map<String, Object> map = new HashMap<>();
        map.put("content", "https://www.baidu.com/");
        map.put("filePath", "D:\\Sevendays\\hblog1.png");
        try {
            // Set the size of the QR code, 300*300
            QrConfig config = new QrConfig(300, 300);
            // Set the margin, that is, the margin between the QR code and the background
            config.setMargin(3);
            // Set the foreground color, which is the QR code color (cyan)
            config.setForeColor(Color.CYAN.getRGB());
            // Set background color (gray)
            config.setBackColor(Color.GRAY.getRGB());
            // Generate QR code to file or stream
            QrCodeUtil.generate(map.get("content").toString(), config, FileUtil.file(map.get("filePath").toString()));
            map.put("code", 200);
            map.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 500);
            map.put("status", "error");
        }
        return map;
    }

    @RequestMapping("image3")
    public Map<String, Object> qrcodeImage3() {
        Map<String, Object> map = new HashMap<>();
        map.put("content", "https://www.baidu.com/");
        map.put("filePath", "D:\\Sevendays\\qrcodeWithLogo.png");
        map.put("image", "D:\\Sevendays\\logo.png");
        try {
            QrCodeUtil.generate(
                    map.get("content").toString(), //content
                    QrConfig.create().setImg(map.get("image").toString()), //logo
                    FileUtil.file(map.get("filePath").toString())//output file
            );
            map.put("code", 200);
            map.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 500);
            map.put("status", "error");
        }
        return map;
    }

}