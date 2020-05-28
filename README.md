# Freemarker Expression Editor

## Introduction

Customers want to export plain text (CSV), structured files (XML eg. BMEcat), HTML/PDF for preview or customer communication. They are not able to do this without support by IT people in current PIM 7 and PIM 8 versions. This Editor serve as a tool to our customers to help them create expressions on editor side that would be evaluated to upropriate result type and then exported.

## Technologies  
- Data Repository pattern  
- Spring JdbcTemplate as a database accessor  
- Java 8  
- Freemarker as a template language
- Flexmark (to evaluate markdown template)
- Reactjs for expression toolbox component
