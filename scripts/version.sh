#!/bin/sh
version=$(grep -oE 'versionCode = [0-9]+' app/build.gradle.kts | awk '{print $3}')
new=$((version + 1))
sed -i "s/versionCode = $version/versionCode = $new/" app/build.gradle.kts
printf "Bumped versionCode to %s\n" "$new"
