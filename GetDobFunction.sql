USE [cs560]
GO

/****** Object:  UserDefinedFunction [dbo].[getdob]    Script Date: 5/16/2023 1:39:40 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create function [dbo].[getdob] (@pid uniqueidentifier)
returns datetime
as begin
	declare @dob datetime
	declare @sql nvarchar(max)
	select @dob = (select dob from people where id = @pid)
	return @dob
end
GO


