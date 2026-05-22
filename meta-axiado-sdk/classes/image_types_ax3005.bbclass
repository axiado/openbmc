# Copyright (c) 2021-26 Axiado Corporation (or its affiliates). All rights reserved.

inherit image_types_phosphor

do_build[depends] += "secureboot-image:do_deploy"
