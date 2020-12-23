package com.example.IWatched.services;

import com.example.IWatched.db.Genre;
import com.example.IWatched.db.Movie;
import com.example.IWatched.db.Role;
import com.example.IWatched.db.User;
import com.example.IWatched.repos.MovieRepository;
import com.example.IWatched.repos.RoleRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInitializer implements ApplicationRunner {

  @Autowired
  UserService userService;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  MovieRepository movieRepository;

  @Autowired
  GenreService genreService;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    // Если в базе данных уже есть роль админа, то можно (?) считать, что базу уже инициализировали,
    // поэтому можно не проводить инициализацию заново
    // Возможно можно сделать с другим условием или вовсе как-то иначе
    if (roleRepository.findById(2L).isPresent()) {
      return;
    }

    roleRepository.save(new Role(1L, "ROLE_USER"));
    roleRepository.save(new Role(2L, "ROLE_ADMIN"));

    User user1 = new User("Stepan");
    user1.setPassword("lox");
    user1.setPasswordConfirm("lox");

    User admin = new User("Admin");
    admin.setPassword("admin");
    admin.setPasswordConfirm("admin");

    userService.save(user1);
    userService.saveAdmin(admin);

    genreService.save(new Genre("Комикс"));
    genreService.save(new Genre("Фантастика"));
    genreService.save(new Genre("Драма"));
    genreService.save(new Genre("Боевик"));

    UploadSomeMovies();

  }

  private void UploadSomeMovies() {
    List<Movie> movies = new ArrayList<Movie>();

    Genre comics = new Genre("Комикс");
    Genre fiction = new Genre("Фантастика");
    Genre drama = new Genre("Драма");
    Genre thriller = new Genre("Боевик");

    Movie movie = new Movie("Дюна", 2021);
    movie.addPoster("Dune.jpg");
    Set<Genre> setGenres = new HashSet<Genre>();
    setGenres.add(fiction);
    setGenres.add(drama);
    movie.setGenres(setGenres);
    movies.add(movie);
    movie.setDescription(
        "Человечество расселилось по далёким планетам, а за власть над обитаемым пространством постоянно борются разные могущественные семьи. В центре противостояния оказывается пустынная планета Арракис. Там обитают гигантские песчаные черви, а в пещерах затаились скитальцы-фримены, но её главная ценность — пряность, самое важное вещество во Вселенной. Тот, кто контролирует Арракис, контролирует пряность, а тот, кто контролирует пряность, контролирует Вселенную.");

    movie = new Movie("Довод", 2020);
    movie.addPoster("Tenet.jpg");
    movie.setGenre(fiction);
    movies.add(movie);
    movie.setDescription(
        "После теракта в киевском оперном театре агент ЦРУ объединяется с британской разведкой, чтобы противостоять русскому олигарху, который сколотил состояние на торговле оружием. Для этого агенты используют инверсию времени - технологию будущего, позволяющую времени идти вспять.");

    movie = new Movie("Ирландец", 2019);
    movie.addPoster("The Irishman.jpg");
    movie.setGenre(drama);
    movies.add(movie);
    movie.setDescription(
        "В доме престарелых сильно пожилой мужчина по имени Фрэнк Ширан вспоминает свою жизнь. В 1950-е он работал простым водителем грузовика, совсем не хотел быть гангстером и думал, что маляры - это те, кто красят дома, когда случайно познакомился с криминальным авторитетом Расселом Буфалино. Тот взял парня под своё крыло, начал давать ему небольшие поручения, и вот уже Фрэнк по прозвищу Ирландец сам работает «маляром», мафиозным киллером. Вскоре Рассел сводит его с известным профсоюзным лидером Джимми Хоффой.");

    movie = new Movie("Сиротский Бруклин", 2019);
    movie.addPoster("Motherless Brooklyn.jpg");
    movie.setGenre(drama);
    movies.add(movie);
    movie.setDescription(
        "Лайонел Эссрог, одинокий частный детектив с синдромом Туретта, решает начать расследование убийства своего наставника и единственного друга Фрэнка Минны. У него есть лишь несколько зацепок и сила разума, одержимого поставленной целью, чтобы распутать клубок тщательно скрываемых тайн, которые сохраняют баланс сил в самом Нью-Йорке. Расследование заводит его в джазовые клубы Гарлема, где рекой течет джин, опасные трущобы Бруклина и даже в позолоченные залы политической элиты мегаполиса. Он бросает вызов гангстерам, коррупции и самому опасному человеку в городе, чтобы очистить имя своего друга и спасти женщину, которая может спасти его самого.");

    movie = new Movie("Братья Систерс", 2018);
    movie.addPoster("The Sisters Brothers.jpg");
    movie.setGenre(drama);
    movies.add(movie);
    movie.setDescription(
        "Братья-беспредельщики по фамилии Систерс по заданию таинственного Командора должны прикончить некоего Уорма, золотоискателя-авантюриста. Фартовые по жизни охотники за головами гонятся за новой жертвой, не подозревая, что их разведчик не так прост. Начинается игра на выживание: острый ум против безбашенной силы.");

    movie = new Movie("Мстители Война бесконечности", 2018);
    movie.addPoster("Avengers Infinity War .jpg");
    movie.setGenre(comics);
    movies.add(movie);
    movie.setDescription(
        "Пока Мстители и их союзники продолжают защищать мир от различных опасностей, с которыми не смог бы справиться один супергерой, новая угроза возникает из космоса: Танос. Межгалактический тиран преследует цель собрать все шесть Камней Бесконечности - артефакты невероятной силы, с помощью которых можно менять реальность по своему желанию. Всё, с чем Мстители сталкивались ранее, вело к этому моменту – судьба Земли никогда ещё не была столь неопределённой.");

    movie = new Movie("Дело храбрых", 2017);
    movie.addPoster("Only the Brave .jpg");
    movie.setGenre(drama);
    movies.add(movie);
    movie.setDescription(
        "История о команде пожарных под названием Granite Mountain Hotshots, столкнувшихся в Аризоне с одним из самых смертоносных пожаров в истории.");

    movie = new Movie("Джон Уик 2", 2017);
    movie.addPoster("John Wick Chapter Two .jpg");
    movie.setGenre(thriller);
    movies.add(movie);
    movie.setDescription(
        "Когда бывший коллега Джона решает взять под свой контроль таинственную гильдию убийц, Уик вынужден выйти из отставки. Ведомый кровавой клятвой Джон отправляется в Рим, где ему придется сразиться с одними из самых опасных киллеров в мире.");

    movie = new Movie("Любой ценой", 2016);
    movie.addPoster("Hell or High Water .jpg");
    movie.setGenre(drama);
    movies.add(movie);
    movie.setDescription(
        "Молодой парень Тоби и его недавно вышедший из тюрьмы старший брат Таннер узнают, что на их умершей матери висел огромный кредит. И теперь, если не погасить долг в определённый срок, банк присвоит себе семейное ранчо. Пытаясь в столь короткий срок найти необходимую сумму, Тоби уговаривает брата пойти на невероятно рискованный шаг – ограбить несколько мелких банков. Вооружившись и надев на лица маски, братья отправляются в опасный тур по местным финансовым заведениям, совершенно не подозревая, куда их может завести эта безумная идея.");

    movie = new Movie("Разрушение", 2015);
    movie.addPoster("Demolition .jpg");
    movie.setGenre(drama);
    movies.add(movie);
    movie.setDescription(
        "Когда Дэвис узнал, что его жена умерла, он захотел купить шоколадные конфеты в торговом автомате, но пачка застряла. Пытаясь выяснить, почему он ничего не чувствует по поводу смерти жены, Дэвис начинает писать длинные письма в обслуживающую торговые автоматы фирму. А в письмах он рассказывает о себе и об отношениях с погибшей женой. Скоро ему отвечает Карен, менеджер компании. Попутно Дэвис понимает, что ему совершенно необходимо разобрать холодильник, разломать кабинку туалета, рабочий компьютер и разрушить собственный дом.");

    movieRepository.saveAll(movies);

  }
}
