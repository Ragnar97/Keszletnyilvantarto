USE [master]
GO
/****** Object:  Database [Készlet_teszt]    Script Date: 2019. 12. 12. 17:02:40 ******/
CREATE DATABASE [Készlet_teszt]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Készlet_teszt', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\Készlet_teszt.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Készlet_teszt_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\Készlet_teszt_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [Készlet_teszt] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Készlet_teszt].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Készlet_teszt] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Készlet_teszt] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Készlet_teszt] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Készlet_teszt] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Készlet_teszt] SET ARITHABORT OFF 
GO
ALTER DATABASE [Készlet_teszt] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Készlet_teszt] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Készlet_teszt] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Készlet_teszt] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Készlet_teszt] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Készlet_teszt] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Készlet_teszt] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Készlet_teszt] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Készlet_teszt] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Készlet_teszt] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Készlet_teszt] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Készlet_teszt] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Készlet_teszt] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Készlet_teszt] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Készlet_teszt] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Készlet_teszt] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Készlet_teszt] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Készlet_teszt] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Készlet_teszt] SET  MULTI_USER 
GO
ALTER DATABASE [Készlet_teszt] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Készlet_teszt] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Készlet_teszt] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Készlet_teszt] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Készlet_teszt] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Készlet_teszt] SET QUERY_STORE = OFF
GO
USE [Készlet_teszt]
GO
/****** Object:  User [ndavid97]    Script Date: 2019. 12. 12. 17:02:40 ******/
CREATE USER [ndavid97] WITHOUT LOGIN WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[Beszállító]    Script Date: 2019. 12. 12. 17:02:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Beszállító](
	[beszállítóID] [int] IDENTITY(1,1) NOT NULL,
	[beszállítónév] [varchar](50) NOT NULL,
	[elérhetőségID] [int] NOT NULL,
	[törölt-e] [bit] NOT NULL,
 CONSTRAINT [PK_Beszállító] PRIMARY KEY CLUSTERED 
(
	[beszállítóID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Beszerzés]    Script Date: 2019. 12. 12. 17:02:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Beszerzés](
	[beszerzésID] [int] IDENTITY(1,1) NOT NULL,
	[beszállítóID] [int] NOT NULL,
	[termékID] [int] NOT NULL,
	[mennyiség] [int] NOT NULL,
	[dátum] [date] NOT NULL,
	[törölt-e] [bit] NOT NULL,
 CONSTRAINT [PK_Beszerzés_1] PRIMARY KEY CLUSTERED 
(
	[beszerzésID] ASC,
	[beszállítóID] ASC,
	[termékID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Elérhetőség]    Script Date: 2019. 12. 12. 17:02:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Elérhetőség](
	[elérhetőségID] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](30) NULL,
	[telefon] [varchar](20) NOT NULL,
	[irsz] [varchar](4) NOT NULL,
	[város] [varchar](50) NOT NULL,
	[utca] [varchar](25) NOT NULL,
	[házszám] [int] NOT NULL,
	[weblap] [varchar](25) NULL,
 CONSTRAINT [PK_Elérhetőség] PRIMARY KEY CLUSTERED 
(
	[elérhetőségID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Felhasználó]    Script Date: 2019. 12. 12. 17:02:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Felhasználó](
	[felhnév] [varchar](15) NOT NULL,
	[jelszó] [varchar](max) NOT NULL,
	[jogkör] [bit] NOT NULL,
	[nyomtatottnév] [varchar](25) NOT NULL,
	[törölt-e] [bit] NOT NULL,
 CONSTRAINT [PK_Felhasználó] PRIMARY KEY CLUSTERED 
(
	[felhnév] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Kategória]    Script Date: 2019. 12. 12. 17:02:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Kategória](
	[kategóriaID] [int] IDENTITY(1,1) NOT NULL,
	[kategórianév] [varchar](25) NOT NULL,
 CONSTRAINT [PK_Kategória] PRIMARY KEY CLUSTERED 
(
	[kategóriaID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Rendelés]    Script Date: 2019. 12. 12. 17:02:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Rendelés](
	[rendelésID] [int] NOT NULL,
	[kelt] [date] NOT NULL,
	[vevőID] [int] NOT NULL,
	[termékID] [int] NOT NULL,
	[mennyiség] [int] NOT NULL,
	[végösszeg] [money] NOT NULL,
	[törölt-e] [bit] NOT NULL,
 CONSTRAINT [PK_Rendelés] PRIMARY KEY CLUSTERED 
(
	[rendelésID] ASC,
	[kelt] ASC,
	[vevőID] ASC,
	[termékID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Termék]    Script Date: 2019. 12. 12. 17:02:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Termék](
	[termékID] [int] IDENTITY(1,1) NOT NULL,
	[terméknév] [varchar](50) NOT NULL,
	[kategóriaID] [int] NOT NULL,
	[mennyiség] [int] NOT NULL,
	[egységár] [money] NOT NULL,
	[leírás] [varchar](100) NULL,
	[törölt-e] [bit] NOT NULL,
 CONSTRAINT [PK_Termékek] PRIMARY KEY CLUSTERED 
(
	[termékID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Vevő]    Script Date: 2019. 12. 12. 17:02:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Vevő](
	[vevőID] [int] IDENTITY(1,1) NOT NULL,
	[vevőnév] [varchar](50) NOT NULL,
	[elérhetőségID] [int] NOT NULL,
	[törölt-e] [bit] NOT NULL,
 CONSTRAINT [PK_Vevő] PRIMARY KEY CLUSTERED 
(
	[vevőID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Beszállító] ON 

INSERT [dbo].[Beszállító] ([beszállítóID], [beszállítónév], [elérhetőségID], [törölt-e]) VALUES (1, N'Gastronic KFT', 1, 0)
INSERT [dbo].[Beszállító] ([beszállítóID], [beszállítónév], [elérhetőségID], [törölt-e]) VALUES (2, N'ujbesz', 8, 0)
INSERT [dbo].[Beszállító] ([beszállítóID], [beszállítónév], [elérhetőségID], [törölt-e]) VALUES (9, N'ujbesz2', 8, 0)
INSERT [dbo].[Beszállító] ([beszállítóID], [beszállítónév], [elérhetőségID], [törölt-e]) VALUES (11, N'ExcelBeszállító', 37, 0)
INSERT [dbo].[Beszállító] ([beszállítóID], [beszállítónév], [elérhetőségID], [törölt-e]) VALUES (12, N'ExcelBeszállító2', 37, 0)
SET IDENTITY_INSERT [dbo].[Beszállító] OFF
SET IDENTITY_INSERT [dbo].[Beszerzés] ON 

INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (14, 1, 12, 150, CAST(N'2019-04-02' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (15, 2, 12, 20, CAST(N'2019-04-02' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (16, 1, 57, 10, CAST(N'2019-04-08' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (22, 1, 65, 10, CAST(N'2019-04-08' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (23, 1, 65, 10, CAST(N'2019-04-08' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (1009, 1, 65, 40, CAST(N'2019-05-20' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (1013, 2, 1058, 2000, CAST(N'2019-05-20' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (1014, 2, 1058, 2000, CAST(N'2019-05-20' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (1015, 2, 1058, 2000, CAST(N'2019-05-20' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (1016, 2, 1058, 2000, CAST(N'2019-05-20' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (1017, 2, 1058, 2000, CAST(N'2019-05-20' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (1018, 2, 1058, 2000, CAST(N'2019-05-20' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (1019, 2, 1058, 2000, CAST(N'2019-05-20' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (1061, 1, 1082, 15, CAST(N'2019-12-09' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (1062, 1, 1084, 300, CAST(N'2019-12-09' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (1063, 1, 1084, 300, CAST(N'2019-12-09' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (1064, 1, 1084, 300, CAST(N'2019-12-12' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (1065, 1, 1084, 300, CAST(N'2019-12-12' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (1067, 1, 1084, 300, CAST(N'2019-12-12' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (1068, 1, 1085, 300, CAST(N'2019-12-12' AS Date), 0)
INSERT [dbo].[Beszerzés] ([beszerzésID], [beszállítóID], [termékID], [mennyiség], [dátum], [törölt-e]) VALUES (1069, 1, 1086, 100, CAST(N'2019-12-12' AS Date), 0)
SET IDENTITY_INSERT [dbo].[Beszerzés] OFF
SET IDENTITY_INSERT [dbo].[Elérhetőség] ON 

INSERT [dbo].[Elérhetőség] ([elérhetőségID], [email], [telefon], [irsz], [város], [utca], [házszám], [weblap]) VALUES (1, N'nda@gmail.com', N'205456681', N'2220', N'Vecsés', N'Halmy', 54, N'www.hu')
INSERT [dbo].[Elérhetőség] ([elérhetőségID], [email], [telefon], [irsz], [város], [utca], [házszám], [weblap]) VALUES (3, N'nda2', N'0630', N'2220', N'Budapest', N'Előd', 26, NULL)
INSERT [dbo].[Elérhetőség] ([elérhetőségID], [email], [telefon], [irsz], [város], [utca], [házszám], [weblap]) VALUES (4, N'mail', N'0620545681', N'2220', N've', N'el', 54, N'web')
INSERT [dbo].[Elérhetőség] ([elérhetőségID], [email], [telefon], [irsz], [város], [utca], [házszám], [weblap]) VALUES (5, N'mail', N'06205456681', N'2220', N've', N'el', 54, N'web')
INSERT [dbo].[Elérhetőség] ([elérhetőségID], [email], [telefon], [irsz], [város], [utca], [házszám], [weblap]) VALUES (6, N'teszt@gmail.com', N'1997', N'2220', N'vecsés', N'el', 54, N'webteszt')
INSERT [dbo].[Elérhetőség] ([elérhetőségID], [email], [telefon], [irsz], [város], [utca], [házszám], [weblap]) VALUES (7, N'besz', N'00', N'0', N'besz', N'besz', 0, N'webteszt')
INSERT [dbo].[Elérhetőség] ([elérhetőségID], [email], [telefon], [irsz], [város], [utca], [házszám], [weblap]) VALUES (8, N'besz', N'205456681', N'1000', N'besz', N'besz', 10, N'besz')
INSERT [dbo].[Elérhetőség] ([elérhetőségID], [email], [telefon], [irsz], [város], [utca], [házszám], [weblap]) VALUES (35, N'@mail', N'205456991', N'1234', N'Győr', N'fa', 41, N'web.hu')
INSERT [dbo].[Elérhetőség] ([elérhetőségID], [email], [telefon], [irsz], [város], [utca], [házszám], [weblap]) VALUES (37, N'@gmail', N'205456771', N'2000', N'Budapest', N'Alkotmány', 51, N'web2.hu')
SET IDENTITY_INSERT [dbo].[Elérhetőség] OFF
INSERT [dbo].[Felhasználó] ([felhnév], [jelszó], [jogkör], [nyomtatottnév], [törölt-e]) VALUES (N'tesztEladó', N'fb8e20fc2e4c3f248c60c39bd652f3c1347298bb977b8b4d593b850556263', 0, N'tesztEladó', 0)
INSERT [dbo].[Felhasználó] ([felhnév], [jelszó], [jogkör], [nyomtatottnév], [törölt-e]) VALUES (N'tesztMenedzser', N'fb8e20fc2e4c3f248c60c39bd652f3c1347298bb977b8b4d593b850556263', 1, N'tesztMenedzser', 0)
SET IDENTITY_INSERT [dbo].[Kategória] ON 

INSERT [dbo].[Kategória] ([kategóriaID], [kategórianév]) VALUES (7, N'műszer')
INSERT [dbo].[Kategória] ([kategóriaID], [kategórianév]) VALUES (14, N'késztermék')
INSERT [dbo].[Kategória] ([kategóriaID], [kategórianév]) VALUES (15, N'egyszerűtermék')
INSERT [dbo].[Kategória] ([kategóriaID], [kategórianév]) VALUES (16, N'szerszám')
INSERT [dbo].[Kategória] ([kategóriaID], [kategórianév]) VALUES (17, N'alkatrész')
SET IDENTITY_INSERT [dbo].[Kategória] OFF
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (1, CAST(N'2019-03-24' AS Date), 4, 12, 12, 120000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (1, CAST(N'2019-03-24' AS Date), 5, 19, 19, 1900.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (3, CAST(N'2019-03-27' AS Date), 4, 12, 100, 1000000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (4, CAST(N'2019-03-27' AS Date), 5, 12, 10, 100000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (5, CAST(N'2019-03-27' AS Date), 4, 19, 20, 2000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (7, CAST(N'2019-03-28' AS Date), 4, 12, 10, 100000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (8, CAST(N'2019-03-28' AS Date), 5, 19, 10, 1000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (11, CAST(N'2019-03-30' AS Date), 4, 19, 10, 1000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (17, CAST(N'2019-04-01' AS Date), 4, 19, 20, 2000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (17, CAST(N'2019-04-01' AS Date), 4, 22, 10, 20000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (18, CAST(N'2019-04-01' AS Date), 4, 19, 4, 400.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (19, CAST(N'2019-04-01' AS Date), 4, 19, 2, 200.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (19, CAST(N'2019-04-01' AS Date), 4, 22, 2, 4000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (20, CAST(N'2019-04-01' AS Date), 4, 19, 2, 200.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (20, CAST(N'2019-04-01' AS Date), 4, 22, 2, 4000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (21, CAST(N'2019-04-01' AS Date), 4, 22, 2, 4000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (22, CAST(N'2019-04-01' AS Date), 4, 19, 2, 200.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (23, CAST(N'2019-05-21' AS Date), 4, 12, 100, 1000000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (23, CAST(N'2019-05-21' AS Date), 4, 19, 10, 1000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (24, CAST(N'2019-05-21' AS Date), 5, 12, 100, 1000000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (24, CAST(N'2019-05-21' AS Date), 5, 22, 4, 8000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (25, CAST(N'2019-05-21' AS Date), 5, 30, 1, 1500.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (25, CAST(N'2019-05-21' AS Date), 5, 57, 5, 7500.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (26, CAST(N'2019-05-21' AS Date), 4, 12, 100, 1000000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (26, CAST(N'2019-05-21' AS Date), 4, 22, 10, 20000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (27, CAST(N'2019-05-21' AS Date), 4, 12, 100, 1000000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (27, CAST(N'2019-05-21' AS Date), 4, 22, 10, 20000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (27, CAST(N'2019-05-21' AS Date), 5, 12, 100, 1000000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (28, CAST(N'2019-05-21' AS Date), 4, 12, 100, 1000000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (28, CAST(N'2019-05-21' AS Date), 4, 22, 10, 20000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (29, CAST(N'2019-05-21' AS Date), 4, 12, 1, 10000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (29, CAST(N'2019-05-21' AS Date), 4, 22, 2, 4000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (30, CAST(N'2019-05-21' AS Date), 4, 12, 1, 10000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (30, CAST(N'2019-05-21' AS Date), 4, 22, 2, 4000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (31, CAST(N'2019-05-21' AS Date), 4, 57, 10, 15000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (33, CAST(N'2019-05-21' AS Date), 4, 57, 40, 60000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (34, CAST(N'2019-05-21' AS Date), 4, 29, 101, 10100.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (34, CAST(N'2019-05-21' AS Date), 4, 57, 10, 15000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (35, CAST(N'2019-05-21' AS Date), 4, 45, 1, 10000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (35, CAST(N'2019-05-21' AS Date), 4, 57, 1, 1500.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (36, CAST(N'2019-05-21' AS Date), 4, 45, 1, 10000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (37, CAST(N'2019-12-08' AS Date), 5, 19, 20, 2000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (37, CAST(N'2019-12-08' AS Date), 5, 22, 6, 12000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (38, CAST(N'2019-12-08' AS Date), 5, 19, 20, 2000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (38, CAST(N'2019-12-08' AS Date), 5, 45, 8, 80000.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (39, CAST(N'2019-12-12' AS Date), 4, 1082, 2, 400.0000, 0)
INSERT [dbo].[Rendelés] ([rendelésID], [kelt], [vevőID], [termékID], [mennyiség], [végösszeg], [törölt-e]) VALUES (39, CAST(N'2019-12-12' AS Date), 4, 1084, 20, 6000.0000, 0)
SET IDENTITY_INSERT [dbo].[Termék] ON 

INSERT [dbo].[Termék] ([termékID], [terméknév], [kategóriaID], [mennyiség], [egységár], [leírás], [törölt-e]) VALUES (12, N'első', 7, 4598, 10000.0000, N'leírás', 0)
INSERT [dbo].[Termék] ([termékID], [terméknév], [kategóriaID], [mennyiség], [egységár], [leírás], [törölt-e]) VALUES (19, N'?excelTermék', 7, 860, 100.0000, N'leírás', 0)
INSERT [dbo].[Termék] ([termékID], [terméknév], [kategóriaID], [mennyiség], [egységár], [leírás], [törölt-e]) VALUES (22, N'excelTermék2', 7, 1940, 2000.0000, N'leírás', 0)
INSERT [dbo].[Termék] ([termékID], [terméknév], [kategóriaID], [mennyiség], [egységár], [leírás], [törölt-e]) VALUES (29, N'ujTermek', 7, 100, 100.0000, N'leírásaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 0)
INSERT [dbo].[Termék] ([termékID], [terméknév], [kategóriaID], [mennyiség], [egységár], [leírás], [törölt-e]) VALUES (30, N'tesztBeszerzés', 7, 1000, 1500.0000, N'Rövid eszt leírás a beszerzéshez', 0)
INSERT [dbo].[Termék] ([termékID], [terméknév], [kategóriaID], [mennyiség], [egységár], [leírás], [törölt-e]) VALUES (45, N'multiméter', 7, 90, 10000.0000, N'Elektrotechnikai mérőműszer', 0)
INSERT [dbo].[Termék] ([termékID], [terméknév], [kategóriaID], [mennyiség], [egységár], [leírás], [törölt-e]) VALUES (57, N'csavarhúzó', 16, 0, 1500.0000, N'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 0)
INSERT [dbo].[Termék] ([termékID], [terméknév], [kategóriaID], [mennyiség], [egységár], [leírás], [törölt-e]) VALUES (65, N'csavar', 17, 20, 1500.0000, N'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 0)
INSERT [dbo].[Termék] ([termékID], [terméknév], [kategóriaID], [mennyiség], [egységár], [leírás], [törölt-e]) VALUES (1056, N'ex1', 7, 100, 100.0000, N'leírás', 0)
INSERT [dbo].[Termék] ([termékID], [terméknév], [kategóriaID], [mennyiség], [egységár], [leírás], [törölt-e]) VALUES (1058, N'ex2', 7, 16000, 2000.0000, N'leírás', 0)
INSERT [dbo].[Termék] ([termékID], [terméknév], [kategóriaID], [mennyiség], [egységár], [leírás], [törölt-e]) VALUES (1079, N'?ex3', 7, 100, 100.0000, N'leírás', 0)
INSERT [dbo].[Termék] ([termékID], [terméknév], [kategóriaID], [mennyiség], [egységár], [leírás], [törölt-e]) VALUES (1080, N'?ex5', 7, 2000, 2000.0000, N'leírás', 0)
INSERT [dbo].[Termék] ([termékID], [terméknév], [kategóriaID], [mennyiség], [egységár], [leírás], [törölt-e]) VALUES (1081, N'?ex8', 7, 2000, 2000.0000, N'leírás', 0)
INSERT [dbo].[Termék] ([termékID], [terméknév], [kategóriaID], [mennyiség], [egységár], [leírás], [törölt-e]) VALUES (1082, N'teknokol', 7, 13, 200.0000, N'egyszerű ragasztó', 0)
INSERT [dbo].[Termék] ([termékID], [terméknév], [kategóriaID], [mennyiség], [egységár], [leírás], [törölt-e]) VALUES (1084, N'üdítő', 15, 1480, 300.0000, N'gyümölcslé', 0)
INSERT [dbo].[Termék] ([termékID], [terméknév], [kategóriaID], [mennyiség], [egységár], [leírás], [törölt-e]) VALUES (1085, N'exMűszer', 7, 300, 300.0000, N'leírás2', 0)
INSERT [dbo].[Termék] ([termékID], [terméknév], [kategóriaID], [mennyiség], [egységár], [leírás], [törölt-e]) VALUES (1086, N'exMűszer2', 7, 100, 100.0000, N'leírás2', 0)
SET IDENTITY_INSERT [dbo].[Termék] OFF
SET IDENTITY_INSERT [dbo].[Vevő] ON 

INSERT [dbo].[Vevő] ([vevőID], [vevőnév], [elérhetőségID], [törölt-e]) VALUES (4, N'Nagy Dávid', 6, 0)
INSERT [dbo].[Vevő] ([vevőID], [vevőnév], [elérhetőségID], [törölt-e]) VALUES (5, N'Kocsis Csaba', 6, 0)
INSERT [dbo].[Vevő] ([vevőID], [vevőnév], [elérhetőségID], [törölt-e]) VALUES (17, N'Fehér Zoltán', 6, 0)
INSERT [dbo].[Vevő] ([vevőID], [vevőnév], [elérhetőségID], [törölt-e]) VALUES (21, N'Gastronic KFT', 1, 0)
INSERT [dbo].[Vevő] ([vevőID], [vevőnév], [elérhetőségID], [törölt-e]) VALUES (49, N'?ExcelVevő', 35, 0)
INSERT [dbo].[Vevő] ([vevőID], [vevőnév], [elérhetőségID], [törölt-e]) VALUES (50, N'ExcelVevő2', 35, 0)
SET IDENTITY_INSERT [dbo].[Vevő] OFF
ALTER TABLE [dbo].[Beszállító] ADD  CONSTRAINT [DF_Beszállító_törölt-e]  DEFAULT ((0)) FOR [törölt-e]
GO
ALTER TABLE [dbo].[Beszerzés] ADD  CONSTRAINT [DF_Beszerzés_dátum]  DEFAULT (getdate()) FOR [dátum]
GO
ALTER TABLE [dbo].[Beszerzés] ADD  CONSTRAINT [DF_Beszerzés_törölt-e]  DEFAULT ((0)) FOR [törölt-e]
GO
ALTER TABLE [dbo].[Felhasználó] ADD  CONSTRAINT [DF_Felhasználó_jogkör]  DEFAULT ((0)) FOR [jogkör]
GO
ALTER TABLE [dbo].[Felhasználó] ADD  CONSTRAINT [DF_Felhasználó_törölt-e]  DEFAULT ((0)) FOR [törölt-e]
GO
ALTER TABLE [dbo].[Rendelés] ADD  CONSTRAINT [DF_Rendelés_kelt]  DEFAULT (getdate()) FOR [kelt]
GO
ALTER TABLE [dbo].[Rendelés] ADD  CONSTRAINT [DF_Rendelés_törölt-e]  DEFAULT ((0)) FOR [törölt-e]
GO
ALTER TABLE [dbo].[Termék] ADD  CONSTRAINT [DF_Termékek_törölt-e]  DEFAULT ((0)) FOR [törölt-e]
GO
ALTER TABLE [dbo].[Vevő] ADD  CONSTRAINT [DF_Vevő_törölt-e]  DEFAULT ((0)) FOR [törölt-e]
GO
ALTER TABLE [dbo].[Beszállító]  WITH CHECK ADD  CONSTRAINT [FK_Beszállító_Elérhetőség] FOREIGN KEY([elérhetőségID])
REFERENCES [dbo].[Elérhetőség] ([elérhetőségID])
GO
ALTER TABLE [dbo].[Beszállító] CHECK CONSTRAINT [FK_Beszállító_Elérhetőség]
GO
ALTER TABLE [dbo].[Beszerzés]  WITH CHECK ADD  CONSTRAINT [FK_Beszerzés_Beszállító] FOREIGN KEY([beszállítóID])
REFERENCES [dbo].[Beszállító] ([beszállítóID])
GO
ALTER TABLE [dbo].[Beszerzés] CHECK CONSTRAINT [FK_Beszerzés_Beszállító]
GO
ALTER TABLE [dbo].[Beszerzés]  WITH CHECK ADD  CONSTRAINT [FK_Beszerzés_Termékek] FOREIGN KEY([termékID])
REFERENCES [dbo].[Termék] ([termékID])
GO
ALTER TABLE [dbo].[Beszerzés] CHECK CONSTRAINT [FK_Beszerzés_Termékek]
GO
ALTER TABLE [dbo].[Rendelés]  WITH CHECK ADD  CONSTRAINT [FK_Rendelés_Termékek] FOREIGN KEY([termékID])
REFERENCES [dbo].[Termék] ([termékID])
GO
ALTER TABLE [dbo].[Rendelés] CHECK CONSTRAINT [FK_Rendelés_Termékek]
GO
ALTER TABLE [dbo].[Rendelés]  WITH CHECK ADD  CONSTRAINT [FK_Rendelés_Vevő] FOREIGN KEY([vevőID])
REFERENCES [dbo].[Vevő] ([vevőID])
GO
ALTER TABLE [dbo].[Rendelés] CHECK CONSTRAINT [FK_Rendelés_Vevő]
GO
ALTER TABLE [dbo].[Termék]  WITH CHECK ADD  CONSTRAINT [FK_Termékek_Kategória] FOREIGN KEY([kategóriaID])
REFERENCES [dbo].[Kategória] ([kategóriaID])
GO
ALTER TABLE [dbo].[Termék] CHECK CONSTRAINT [FK_Termékek_Kategória]
GO
ALTER TABLE [dbo].[Vevő]  WITH CHECK ADD  CONSTRAINT [FK_Vevő_Elérhetőség] FOREIGN KEY([elérhetőségID])
REFERENCES [dbo].[Elérhetőség] ([elérhetőségID])
GO
ALTER TABLE [dbo].[Vevő] CHECK CONSTRAINT [FK_Vevő_Elérhetőség]
GO
USE [master]
GO
ALTER DATABASE [Készlet_teszt] SET  READ_WRITE 
GO
