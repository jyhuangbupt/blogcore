package com.henry.blog.util;

import java.util.List;
import java.util.Random;

public class UuidGenerator {
    public static void main(String args[]) {
        List<Integer> userIds = null;
        System.out.println(generateUserId(userIds));
    }

    public static String generateUuid(Integer id, String dataBase, String tableSimpleName, Integer tableNumber) {
        StringBuilder generateUuid = new StringBuilder();
        generateUuid.append(generateIdPartOfUuid(id));
        generateUuid.append(dataBase);
        generateUuid.append(generateTablePartOfUuid(tableSimpleName, tableNumber));
        return generateUuid.toString();
    }

    public static Integer generateUserId(List<Integer> userIds){
        int min = 8800000;
        if(userIds != null){
            while (min <= 8899999){
                if(userIds.contains(min)){
                    min++;
                }else {
                    return  min;
                }
            }
            return  -1;
        }else {
            return  min;
        }
    }
    
    public static StringBuilder generateTablePartOfUuid(String tableSimpleName, Integer tableNumber) {
        StringBuilder tablePart = new StringBuilder();
        Random random = new Random();
        tablePart.append(tableSimpleName);
        char[] letter = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (int i = 0; i < (6 - tableSimpleName.length()); i++) {
            tablePart.append(letter[random.nextInt(letter.length)]);
        }
        for (int i = 0; i < (3 - tableNumber.toString().length()); i++) {
            tablePart.append(0);
        }
        tablePart.append(tableNumber);
        return tablePart;
    }

    public static StringBuilder generateIdPartOfUuid(Integer id) {
        StringBuilder idPart = new StringBuilder();
        for (int i = 0; i < (6 - id.toString().length()); i++) {
            idPart.append(0);
        }
        idPart.append(id);
        return idPart;
    }
}
