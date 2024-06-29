package com.retroStore.controller.master;

import com.retroStore.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.prefix.v1}/user")
public class SelectedFolderController extends BaseController {

}
