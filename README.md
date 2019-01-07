# BuffCore

It is a Mod of Grarak's [KernelAdiutor](https://github.com/Grarak/KernelAdiutor) for compatibilize to Samsung Exynos.
Thanks to Willi Ye for this great application.

# Building

You will surely need 

    sudo apt install openjdk-8-jdk

Also, you need to download android studio in order to use it's SDK manager. Install the packages needed by
./gradlew build

If you are on updated Linux Distro you may need to switch to 1.8 java in order to build

    sudo update-alternatives --config java

# Useful commands

find 'apple' and replace it with 'orange' in all files and all directories/subdirectories


        find ./ -type f -exec sed -i -e 's/apple/orange/g' {} \;
