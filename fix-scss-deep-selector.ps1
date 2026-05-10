# Fix SCSS deep selector syntax
# Replace /deep/ with ::v-deep

$adminPath = "D:\system\medicineresource\src\main\resources\admin"
$frontPath = "D:\system\medicineresource\src\main\resources\front"

Write-Host "=== Start fixing SCSS deep selectors ===" -ForegroundColor Green

# Fix admin directory
Write-Host "`nFixing admin directory..." -ForegroundColor Yellow
Get-ChildItem -Path $adminPath -Filter "*.vue" -Recurse | ForEach-Object {
    $content = Get-Content $_.FullName -Raw -Encoding UTF8
    if ($content -match '/deep/') {
        $newContent = $content -replace '/deep/', '::v-deep'
        Set-Content -Path $_.FullName -Value $newContent -Encoding UTF8 -NoNewline
        Write-Host "  Fixed: $($_.Name)" -ForegroundColor Cyan
    }
}

# Fix front directory
Write-Host "`nFixing front directory..." -ForegroundColor Yellow
Get-ChildItem -Path $frontPath -Filter "*.vue" -Recurse | ForEach-Object {
    $content = Get-Content $_.FullName -Raw -Encoding UTF8
    if ($content -match '/deep/') {
        $newContent = $content -replace '/deep/', '::v-deep'
        Set-Content -Path $_.FullName -Value $newContent -Encoding UTF8 -NoNewline
        Write-Host "  Fixed: $($_.Name)" -ForegroundColor Cyan
    }
}

Write-Host "`n=== Fix completed! ===" -ForegroundColor Green
Write-Host "Please restart the frontend projects" -ForegroundColor Yellow
