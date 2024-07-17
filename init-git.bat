
@echo off
for %%I in ("%cd%") do set current_dir=%%~nxI
echo Current directory name is: %current_dir%

git init
git remote add origin0 git@github.liujia:liujia13142324/%current_dir%.git
git remote add origin1 git@gitlab.liujia:test/%current_dir%.git

git add . 
git commit -m 'commit'
git push origin0 master
git push origin1 master 