/*
 * Copyright 2000-2009 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.execution;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PsiLocation<E extends PsiElement> extends Location<E> {
  private static final Logger LOG = Logger.getInstance("#com.intellij.execution.PsiLocation");
  private final E myPsiElement;
  private final Project myProject;
  private final Module myModule;

  public PsiLocation(@NotNull final Project project, @NotNull final E psiElement) {
    myPsiElement = psiElement;
    myProject = project;
    myModule = ModuleUtil.findModuleForPsiElement(psiElement);
  }

  public PsiLocation(Project project, Module module, E psiElement) {
    myPsiElement = psiElement;
    myProject = project;
    myModule = module;
  }

  @NotNull
  public E getPsiElement() {
    return myPsiElement;
  }

  @NotNull
  public Project getProject() {
    return myProject;
  }

  @Override
  public Module getModule() {
    return myModule;
  }

  @NotNull
  public <T extends PsiElement> Iterator<Location<T>> getAncestors(final Class<T> ancestorClass, final boolean strict) {
    final T first;
    if (!strict && ancestorClass.isInstance(myPsiElement)) first = (T)myPsiElement;
    else first = findNext(myPsiElement, ancestorClass);
    return new Iterator<Location<T>>() {
      private T myCurrent = first;
      public boolean hasNext() {
        return myCurrent != null;
      }

      public Location<T> next() {
        if (myCurrent == null) throw new NoSuchElementException();
        final PsiLocation<T> psiLocation = new PsiLocation<T>(myProject, myCurrent);
        myCurrent = findNext(myCurrent, ancestorClass);
        return psiLocation;
      }

      public void remove() {
        LOG.assertTrue(false);
      }
    };
  }

  public PsiLocation<E> toPsiLocation() {
    return this;
  }

  private static <ElementClass extends PsiElement> ElementClass findNext(final PsiElement psiElement, final Class<ElementClass> ancestorClass) {
    PsiElement element = psiElement;
    while ((element = element.getParent()) != null && !(element instanceof PsiFile)) {
      final ElementClass ancestor = safeCast(element, ancestorClass);
      if (ancestor != null) return ancestor;
    }
    return null;
  }

  public static <T extends PsiElement> Location<T> fromPsiElement(final Project project, final T element) {
    if (element == null) return null;
    return new PsiLocation<T>(project, element);
  }

  public static <T extends PsiElement> Location<T> fromPsiElement(final T element) {
    return fromPsiElement(element, null);
  }

  public static <T extends PsiElement> Location<T> fromPsiElement(T element, Module module) {
    if (element == null) return null;
    if (!element.isValid()) return null;
    return module != null ? new PsiLocation<T>(element.getProject(), module, element) : new PsiLocation<T>(element.getProject(), element);
  }
}
