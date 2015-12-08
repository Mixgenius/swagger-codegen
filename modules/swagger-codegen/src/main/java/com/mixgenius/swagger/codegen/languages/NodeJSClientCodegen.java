package com.mixgenius.swagger.codegen.languages;

import com.wordnik.swagger.codegen.languages.NodeJSServerCodegen;
import com.wordnik.swagger.codegen.*;
import com.wordnik.swagger.models.properties.*;
import com.wordnik.swagger.models.*;
import java.util.*;
import java.io.File;

public class NodeJSClientCodegen extends NodeJSCustomCodegen {

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

  @Override
  public Map<String, Object> postProcessModels(Map<String, Object> objs) {
    List<Object> models = (List<Object>) objs.get("models");
    for (Object _mo : models) {
        Map<String, Object> mo = (Map<String, Object>) _mo;
        CodegenModel cm = (CodegenModel) mo.get("model");
        for (CodegenProperty var : cm.vars) {
            if(var.complexType != null && !var.complexType.isEmpty() && var.complexType != cm.classname) {
              cm.complexTypes.add(var.complexType);
            }
        }
    }
    return objs;
  }

  @Override
  public Map<String, Object> postProcessOperations(Map<String, Object> objs) {
    Map<String, Object> objectMap = (Map<String, Object>)objs.get("operations");
    List<CodegenOperation> operations = (List<CodegenOperation>)objectMap.get("operation");
    for(CodegenOperation operation : operations) {
      // Ugly code to get the operation nickname, the way Michael wants :P
      String newNickname = operation.nickname.replace(operation.baseName+"_", "");
      newNickname = newNickname.substring(0,1).toLowerCase() + newNickname.substring(1);
      operation.nickname = newNickname;
    }
    return objs;
  }
}
