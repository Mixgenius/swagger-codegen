package com.mixgenius.swagger.codegen.languages;

import com.wordnik.swagger.codegen.languages.NodeJSServerCodegen;
import com.wordnik.swagger.codegen.*;
import com.wordnik.swagger.models.properties.*;

import java.util.*;
import java.io.File;

public class NodeJSCustomCodegen extends NodeJSServerCodegen {

  public NodeJSCustomCodegen() {
    super();

    // Primitive objects
    languageSpecificPrimitives = new HashSet<String>(
      Arrays.asList(
        "String",
        "boolean",
        "Boolean",
        "Double",
        "Integer",
        "Long",
        "Float",
        "DateTime")
      );
  }

  public String toModelName(String name) {
    // Replace the Dto suffix from generated class
    return super.toModelName(name).replaceAll("Dto", "");
  }

  public String toModelFilename(String name) {
    // Replace the Dto suffix from generated class
    return super.toModelFilename(name).replaceAll("Dto", "");
  }

  public String toDefaultValue(Property p) {
    if(p instanceof StringProperty)
      return "null";
    else if (p instanceof BooleanProperty)
      return "false";
    else if(p instanceof DateProperty)
      return "null";
    else if(p instanceof DateTimeProperty)
      return "null";
    else if (p instanceof DoubleProperty)
      return "0.0";
    else if (p instanceof FloatProperty)
      return "0.0";
    else if (p instanceof IntegerProperty)
      return "0";
    else if (p instanceof LongProperty)
      return "0";
    else if (p instanceof MapProperty) {
      return "[]";
    }
    else if (p instanceof ArrayProperty) {
      return "[]";
    }
    else
      return "null";
  }

  @Override
  public String getSwaggerType(Property p) {
    return toModelName(super.getSwaggerType(p));
  }
}