package com.example.productservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class SampleController {


    @GetMapping("/")
    public String sayHello(){
        return "hello";
    }

    @GetMapping("/{name}/{city}")
    public String sayName(@PathVariable("name") String name, @PathVariable("city")String city){
        return "hello says  " + name + "  "+ city;
    }


    @GetMapping("/count/{name}/{number}")
    public String countName(@PathVariable("name") String name,@PathVariable("number") int number){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<number;i++){
            sb.append(name);
        }
        return sb.toString();
    }
}
