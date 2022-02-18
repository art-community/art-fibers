import org.gradle.internal.os.OperatingSystem.current

/*
 * ART
 *
 * Copyright 2019-2022 ART
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    `java-library`
    id("art-internal-jvm")
}

dependencies {
    val graalVersion: String by project
    val lombokVersion: String by project
    val javaModulesVersion: String by project

    compileOnly("org.graalvm.nativeimage", "svm", graalVersion)
    compileOnly("org.projectlombok", "lombok", lombokVersion)
    annotationProcessor("org.projectlombok", "lombok", lombokVersion)

    embedded("io.art.java:core:$javaModulesVersion")
    embedded("io.art.java:meta:$javaModulesVersion")
    embedded("io.art.java:logging:$javaModulesVersion")
}

executable {
    main("io.art.fibers.Fibers")
    native()
}

sources {
    cmake("coroutine") {
        directory(projectDir.resolve("dependencies").toPath())
        if (current().isWindows) copy("Release/coroutine.lib", "src/main/resources/coroutine-windows.lib")
        if (current().isLinux) copy("libcoroutine.a", "src/main/resources/libcoroutine-linux.a")
        if (current().isMacOsX) copy("libcoroutine.a", "src/main/resources/libcoroutine-osx.a")
    }
}
