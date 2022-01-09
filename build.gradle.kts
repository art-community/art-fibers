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
    id("art-jvm")
    `java-library`
}

group = "io.art.example"

tasks.withType(type = Wrapper::class) {
    gradleVersion = "7.2"
}

allprojects {
    group = rootProject.group
    repositories {
        mavenCentral()
    }
}

art {
    modules {
        embedded {
            java {
                core()
                meta()
                logging()
            }
        }
    }
    executable {
        main("io.art.fibers.Fibers")
        native()
    }
}

dependencies {
    val graalVersion: String by project
    val lombokVersion: String by project

    compileOnly("org.graalvm.nativeimage", "svm", graalVersion)
    compileOnly("org.projectlombok", "lombok", lombokVersion)
    annotationProcessor("org.projectlombok", "lombok", lombokVersion)
}
