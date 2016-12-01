package com.scientific.calc.Controllers;

import com.scientific.calc.Models.TransferNumber;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private List<TransferNumber> numbers = new ArrayList<>();

	@RequestMapping(method = RequestMethod.GET)
	public String indexGet(ModelMap model) {
		model.addAttribute("numbers", numbers);
		return "/index";
	}

    @RequestMapping(method = RequestMethod.POST)
    public String indexPost(@ModelAttribute("decimal") String decimal, ModelMap model) {
        numbers.add(new TransferNumber(new BigInteger(decimal).toString()));
        model.addAttribute("numbers", numbers);
        return "/index";
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/transfer", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody TransferNumber indexGetNumber(@RequestParam("number") String number, @RequestParam("name") String name) {
        TransferNumber transfer;

        switch (name) {
            // Указываем, что number является двоичным кодом и что его нужно перевести в десятичный
            case "binary": transfer = new TransferNumber(new BigInteger(number, 2).toString(10));
                break;
            case "octal": transfer = new TransferNumber(new BigInteger(number, 8).toString(10));
                break;
            case "decimal": transfer = new TransferNumber(new BigInteger(number, 10).toString(10));
                break;
            case "hexadecimal": transfer = new TransferNumber(new BigInteger(number, 16).toString(10));
                break;
            default: transfer = new TransferNumber(new BigInteger("0").toString());
        }

        return transfer;
    }

    @RequestMapping(value = "/pageCalc", method = RequestMethod.GET)
    public String pageCalcGet() {
        return "/pageCalc";
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/calc", method = RequestMethod.GET, produces = { "text/html; charset=UTF-8" })
    public @ResponseBody String calcGet(@RequestParam("format") String format,
                                                @RequestParam("first") String first,
                                                @RequestParam("second") String second,
                                                @RequestParam("operation") String operation) {

        // Получаем формат в виде числа
        int code = returnFormat(format);

        switch (operation) {
            case "+": return new BigInteger(first, code).add(new BigInteger(second, code)).toString(code);
            case "-": return new BigInteger(first, code).subtract(new BigInteger(second, code)).toString(code);
            default: return "0";
        }
    }

    /**
     * Для получения формата в виде числа
     * @param format Передаем название желаемого формата
     * @return Получаем формат в виде числа
     */
    private int returnFormat(String format) {
        switch(format) {
            case "binary": return 2;
            case "octal": return 8;
            case "decimal": return 10;
            case "hexadecimal": return 16;
            default: return 0;
        }
    }
}