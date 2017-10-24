package edu.hm.cs.cnj.cnjbackend.info;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class InfoController {
@RequestMapping("/info")
public Info greeting() {
Info result = new Info();
result.setMessage("OK");
return result;
}
}