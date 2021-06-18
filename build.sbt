Global / scalaVersion := "2.13.5"
Global / organization := "com.tochka"
Global / name := "test-tasks"
Global / version := "0.1"

lazy val root = project
  .in(file("."))
  .aggregate(tasks)

lazy val tasks = project.in(file("tasks"))
