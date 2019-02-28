#!/usr/bin/env bash
sh build_admin.sh
scp admin/admin-application/target/admin-application-1.0-SNAPSHOT.jar runner@192.168.88.10:/work2/project/admin