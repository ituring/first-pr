# GitHub实践入门 ~ Pull Request引发的开发变革

特设网站

  [http://ituring.github.io/first-pr/](http://ituring.github.io/first-pr/)

Git冲突时的解决方法

-  [Resolving merge conflicts](https://help.github.com/articles/resolving-merge-conflicts/)
-  [Resolving a merge conflict from the command line](https://help.github.com/articles/resolving-a-merge-conflict-from-the-command-line/)

创建并提交项目

```
echo "pull_request_demo from A" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/A/pull_request_demo.git
git push -u origin main
```