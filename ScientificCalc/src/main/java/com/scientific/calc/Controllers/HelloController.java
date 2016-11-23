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
public class HelloController {

    private List<TransferNumber> numbers = new ArrayList<>();

	@RequestMapping(method = RequestMethod.GET)
	public String indexGet(ModelMap model) {
		model.addAttribute("numbers", numbers);
		return "index";
	}

    @RequestMapping(method = RequestMethod.POST)
    public String indexPost(@ModelAttribute("decimal") String decimal, ModelMap model) {
        numbers.add(new TransferNumber(new BigInteger(decimal).toString()));
        model.addAttribute("numbers", numbers);
        return "index";
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/transfer", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody TransferNumber indexGetBinary(@RequestParam("number") String number, @RequestParam("name") String name) {
        TransferNumber transfer;

        switch (name) {
            // Указываем, что number является двоичным кодом и что его нужно перевести в десятичный
            case "binary": transfer = new TransferNumber(new BigInteger(number, 2).toString(10));
                break;
            case "octal": transfer = new TransferNumber(new BigInteger(number, 8).toString(10));
                break;
            case "decimal": transfer = new TransferNumber(new BigInteger(number, 10).toString(10));
                break;
            case "hex": transfer = new TransferNumber(new BigInteger(number, 16).toString(10));
                break;
            default: transfer = new TransferNumber(new BigInteger("0").toString());
        }

        return transfer;
    }
}