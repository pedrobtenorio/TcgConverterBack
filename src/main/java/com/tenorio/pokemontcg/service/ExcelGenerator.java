package com.tenorio.pokemontcg.service;

import com.tenorio.pokemontcg.domain.Card;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


public class ExcelGenerator {

    public ResponseEntity<InputStreamResource> exportToExcel(List<Card> cards, String fileName) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Cards");

        int rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Name");
        row.createCell(2).setCellValue("Number");
        row.createCell(3).setCellValue("Printed Total");
        row.createCell(4).setCellValue("Supertype");
        row.createCell(5).setCellValue("Level");
        row.createCell(6).setCellValue("Artist");
        row.createCell(7).setCellValue("Rarity");
        row.createCell(8).setCellValue("Flavor Text");

        for (Card card : cards) {
            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(card.getId());
            row.createCell(1).setCellValue(card.getName());
            row.createCell(2).setCellValue(card.getNumber());
            row.createCell(3).setCellValue(card.getSet().getPrintedTotal());
            row.createCell(4).setCellValue(card.getSupertype());
            row.createCell(5).setCellValue(card.getLevel());
            row.createCell(7).setCellValue(card.getArtist());
            row.createCell(8).setCellValue(card.getRarity());
            row.createCell(9).setCellValue(card.getFlavorText());
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            workbook.write(stream);
            InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(stream.toByteArray()));
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName+".xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
