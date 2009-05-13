package com.intellij.packaging.impl.artifacts;

import com.intellij.openapi.compiler.CompilerBundle;
import com.intellij.openapi.util.IconLoader;
import com.intellij.packaging.artifacts.ArtifactType;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author nik
 */
public class PlainArtifactType extends ArtifactType {
  public static final Icon ARTIFACT_ICON = IconLoader.getIcon("/nodes/artifact.png");
  @NonNls public static final String ID = "plain";

  public static PlainArtifactType getInstance() {
    return ContainerUtil.findInstance(getAllTypes(), PlainArtifactType.class);
  }

  public PlainArtifactType() {
    super(ID, CompilerBundle.message("artifact.type.plain"));
  }

  @NotNull
  @Override
  public Icon getIcon() {
    return ARTIFACT_ICON;
  }

  public String getDefaultPathForDirectory() {
    return "/";
  }

  public String getDefaultPathForJar() {
    return "..";
  }
}