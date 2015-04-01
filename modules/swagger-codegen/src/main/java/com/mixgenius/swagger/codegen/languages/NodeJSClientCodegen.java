package com.mixgenius.swagger.codegen.languages;

import com.wordnik.swagger.codegen.languages.NodeJSServerCodegen;
import com.wordnik.swagger.codegen.*;
import com.wordnik.swagger.models.properties.*;

import java.util.*;
import java.io.File;

public class NodeJSClientCodegen extends NodeJSServerCodegen {

  public NodeJSClientCodegen() {
    super();

    // Clean model templates, and add ours
    modelTemplateFiles.clear();
    modelTemplateFiles.put("model.mustache", ".js");

    // Clean api template, and add ours
    apiTemplateFiles.clear();
    apiTemplateFiles.put("api.mustache", ".js");

    // Remove previous other files, and add ours
    supportingFiles.clear();
    supportingFiles.add(new SupportingFile("package.mustache", "", "package.json"));
    supportingFiles.add(new SupportingFile("index.mustache", "", "index.js"));
    supportingFiles.add(new SupportingFile("README.mustache", "", "README.md"));

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

  public String modelPackage() {
    return "models";
  }

  public String apiPackage() {
    return "apis";
  }

  public CodegenType getTag() {
    return CodegenType.CLIENT;
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