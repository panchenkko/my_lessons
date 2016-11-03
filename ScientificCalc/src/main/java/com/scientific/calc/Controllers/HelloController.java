package com.scientific.calc.Controllers;

import com.scientific.calc.Models.Transfer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class HelloController {

    private List<Transfer> transfers = new ArrayList<>();

	@RequestMapping(method = RequestMethod.GET)
	public String indexGet(ModelMap model) {
		model.addAttribute("transfers", transfers);
		return "index";
	}

    @RequestMapping(method = RequestMethod.POST)
    public String indexPost(@ModelAttribute("binary") String binary, ModelMap model) {
        transfers.add(new Transfer(new Date(), binary, Transfer.transfer(binary)));
        model.addAttribute("transfers", transfers);
        return "index";
    }
}