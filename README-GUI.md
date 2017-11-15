# Editing the GUI forms

The forms to enter parameters when creating new jobs were originally created using the "Abeille Forms Designer", and exported as Java classes using the designer's source code generation capability.  If you need to edit the forms you can load the XML definitions (in `src/main/resources`) into the designer tool, make your changes, then re-generate the Java classes - it is necessary to post-edit the generated Java code to remove the `import` statement at the top for `I18NUtils`, which is imported but not actually used in the generated code.
