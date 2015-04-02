package com.mixgenius.swagger.codegen.languages;

import com.wordnik.swagger.codegen.*;
import com.wordnik.swagger.models.properties.*;

import java.util.*;
import java.io.File;

public class NodeJSServerMockCodegen extends NodeJSCustomCodegen {

  public NodeJSServerMockCodegen() {
    super();

    // Nothing more for now
  }

  public CodegenType getTag() {
    return CodegenType.SERVER;
  }
}