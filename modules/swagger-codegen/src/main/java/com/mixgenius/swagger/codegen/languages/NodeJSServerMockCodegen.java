package com.mixgenius.swagger.codegen.languages;

import com.wordnik.swagger.codegen.*;
import com.wordnik.swagger.models.properties.*;

import java.util.*;
import java.io.File;

public class NodeJSServerMockCodegen extends NodeJSCustomCodegen {

  public NodeJSServerMockCodegen() {
    super();

    // Nothing more for now
    supportingFiles.clear();
    supportingFiles.add(new SupportingFile("swagger.mustache",
      "spec",
      "swagger.js")
    );
    supportingFiles.add(new SupportingFile("index.mustache",
      "",
      "index.js")
    );
    supportingFiles.add(new SupportingFile("package.mustache",
      "",
      "package.json")
    );
    if(System.getProperty("noservice") == null) {
      apiTemplateFiles.put(
        "service.mustache",   // the template to use
        "Service.js");       // the extension for each file to write
    }
  }

  public CodegenType getTag() {
    return CodegenType.SERVER;
  }
}