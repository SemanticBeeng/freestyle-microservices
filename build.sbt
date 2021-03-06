pgpPassphrase := Some(getEnvVar("PGP_PASSPHRASE").getOrElse("").toCharArray)
pgpPublicRing := file(s"$gpgFolder/pubring.gpg")
pgpSecretRing := file(s"$gpgFolder/secring.gpg")

lazy val root = project
  .in(file("."))
  .settings(name := "freestyle-microservices")
  .settings(moduleName := "root")
  .settings(noPublishSettings: _*)
  .settings(scalaMetaSettings: _*)
  .settings(libraryDependencies ++= commonDeps ++ freestyleCoreDeps())
  .aggregate(`freestyle-microservicesJS`, `freestyle-microservicesJVM`)

lazy val `freestyle-microservices` = crossProject
  .in(file("freestyle-microservices"))
  .settings(moduleName := "freestyle-microservices")
  .settings(scalaMetaSettings: _*)
  .crossDepSettings(commonDeps ++ freestyleCoreDeps(): _*)
  .jsSettings(sharedJsSettings: _*)

lazy val `freestyle-microservicesJVM` = `freestyle-microservices`.jvm
lazy val `freestyle-microservicesJS` = `freestyle-microservices`.js
