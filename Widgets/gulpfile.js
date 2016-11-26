var gulp = require('gulp');
var browserify = require('browserify');
var babelify = require('babelify');
var source = require('vinyl-source-stream');

// Мы говорим Gulp, что нужно использовать Browserify для app.jsx. В дополнение, мы включили режим отладки,
// который будет полезен при разработке.
// Мы добавили трансформацию с помощью Babelify. Это позволит сконвертировать код на ECMAScript 6 в ECMAScript 5.
// Результат будем выводить в файл dist/bundle.js. Также мы подключили необходимые пресеты Babel.
gulp.task('build', function () {
    return browserify({entries: './app.jsx', extensions: ['.jsx'], debug: true})
      .transform('babelify', {presets: ['react', 'es2015', 'stage-0'] , plugins: ['transform-decorators-legacy']})
      .bundle()
      .on('error', function(err) { console.error(err); this.emit('end'); })
      .pipe(source('bundle.js'))
      .pipe(gulp.dest('dist'));
});

// Мы определили задачу с именем watch, которую будем запускать по команде gulp watch. Эта задача будет
// запускать gulp build при каждом изменении jsx файлов.
gulp.task('watch', ['build'], function () {
    gulp.watch('*.jsx', ['build']);
});

// Мы определили задачу gulp, которая будет запускаться по команде gulp. Эта команда просто выполняет задачу watch.
gulp.task('default', ['watch']);
